package com.tikal.spark.reader

import java.text.SimpleDateFormat

import org.codehaus.jackson.map.ObjectMapper

/**
 * WorldCapDictionary 
 *
 * @author Dmitri Krasnenko
 */
object WorldCupDictionary {

  val MAPPER: ObjectMapper = new ObjectMapper()

  val DICTIONARY: List[String] = List("germany", "brasil", "austria", "usa", "argentina", "spain", "france", "israel", "russia", "netherlands")

  val CUP_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss")

  val TWITTER_DATE_FORMAT = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy")

}
