package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Tweet;

@Repository
public interface TweetRepository extends MongoRepository<Tweet,String>{ 

}