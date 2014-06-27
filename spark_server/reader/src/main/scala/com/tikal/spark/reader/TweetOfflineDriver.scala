package com.tikal.spark.reader


import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import org.apache.spark.SparkContext._
import org.apache.spark.streaming.{StreamingContext, Seconds}
import org.bson.BasicBSONObject
import scala.collection.JavaConversions._
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.hadoop.conf.Configuration

import scala.collection.SortedSet
import scala.collection.immutable.TreeMap


/**
 * Tweet offline driver. The driver parses tweets files.
 *
 * Main arguments:
 * --path= following by a tweet files full folder path or full single tweet file path
 * --mongo= following by a mongo db url for example mongodb://127.0.0.1:27017
 * --master= following by Spark master url for example spark://spark.master.local:7077, if not specified 'local' master will be used
 *
 * * Example:
 * --path=/home/fuse_2406/fuse/\* --mongo=mongodb://127.0.0.1:27017
 *
 * @author Dmitri Krasnenko
 */

object TweetOfflineDriver {
  def main (args: Array[String]) {

    //Map command line arguments
    val opts = args.foldLeft(Map[String, String]()) { (m, opt) => {
      val kv = opt.split("=")
      m + (kv(0) -> kv(1))
    }}

    //Validate seeds
    var isValid = opts.contains("--mongo") match {
      case true => true
      case _ => println("No mongo found"); false
    }

    //Validate endpoint URL
    isValid = isValid & opts.contains("--path") match {
      case true => true
      case _ => println("No path found"); false
    }

    //Root folder
    val path = opts("--path")
    //Mongo url
    val mongo = opts("--mongo")

    //Master url
    val master = opts.contains("--master") match {
      case true => opts("--master")
      case    _ => "local[6]"
    }

    val conf = new SparkConf()
      .setMaster(master)
      .set("spark.cleaner.ttl", "600")
      .set("spark.executor.memory", "250m")
      .setAppName("ceilometer_data_streaming")
      .setJars(SparkContext.jarOfClass(this.getClass).toSeq)

    val sc = new SparkContext(conf)

    val tweets = sc.textFile(path).flatMap(tweet => {
      //Create tuples by wanted tags
      WorldCupDictionary.DICTIONARY.filter(tag => tweet.toLowerCase.contains(tag)).map(tag => (tag, tweet))
    }).map(tuple => {
      //Convert JSON string to a tweet object
      (tuple._1, WorldCupDictionary.MAPPER.readValue(tuple._2, classOf[Object2ObjectOpenHashMap[String, Object]]))
    }).map(tuple => {
      //Convert tweet object to a world cup object
      val map = new BasicBSONObject()

      map.put("country", tuple._1)


      map.put("twit", tuple._2("text").toString)
      map.put("source", tuple._2("source").toString.replaceAll("(<a href=\"(.*)\">)|(</a>)", ""))
      map.put("userName", tuple._2("user").asInstanceOf[java.util.Map[String,String]]("screen_name"))

      try {
        map.put("dateInt", WorldCupDictionary.CUP_DATE_FORMAT.format(WorldCupDictionary.TWITTER_DATE_FORMAT.parse(tuple._2("created_at").toString)))
      } catch { case ex: Exception => println(ex)}

      map
    }).cache()

    //Path 1: save to Mongo
    val twitConfig
      = new Configuration()
    twitConfig
      .set("mongo.output.uri", s"$mongo/worldcup.twit")

    tweets.map(tweet => {
      (null, tweet)
    }).saveAsNewAPIHadoopFile("file:///bogus", classOf[Any], classOf[Any], classOf[com.mongodb.hadoop.MongoOutputFormat[Any, Any]], twitConfig)


    //Path 2: source count
    val counters = tweets.map(tweet => (tweet.get("source"), 1)).reduceByKey(_ + _).cache()

    //Path 2.0: pie
    val pieConfig
      = new Configuration()
    pieConfig
      .set("mongo.output.uri", s"$mongo/worldcup.pie")
    counters.map(counter => {
      val map = new BasicBSONObject()

      map.put("count", counter._2)
      map.put("source", counter._1)

      (null, map)

    }).saveAsNewAPIHadoopFile("file:///bogus", classOf[Any], classOf[Any], classOf[com.mongodb.hadoop.MongoOutputFormat[Any, Any]], pieConfig)

    //Path 2.1: top N
    val topnConfig
      = new Configuration()
    topnConfig
      .set("mongo.output.uri", s"$mongo/worldcup.topn")

    val topN = counters
      .aggregate(SortedSet[Int]())((set, counter) => set + counter._2, (set1, set2) => (set1 ++ set2).takeRight(5))

    counters.filter(counter => topN.contains(counter._2)).map(counter => {
      val map = new BasicBSONObject()

      map.put("cont", counter._2)
      map.put("source", counter._1)

      (null, map)
    }).saveAsNewAPIHadoopFile("file:///bogus", classOf[Any], classOf[Any], classOf[com.mongodb.hadoop.MongoOutputFormat[Any, Any]], topnConfig)
  }



}