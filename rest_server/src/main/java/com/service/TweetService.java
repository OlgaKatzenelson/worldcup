package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Tweet;
import com.repository.TweetRepository;

@Service
public class TweetService {

	@Autowired
	private TweetRepository repository;

	public void save(Tweet twit) { 
		repository.save(twit);
		
	}

	public List<Tweet> findAll() {  
		return repository.findAll();
	}
}
