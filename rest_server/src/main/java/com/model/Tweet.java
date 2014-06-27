package com.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tweet {

	private String message;

	public Tweet(){}
	
	public Tweet(String message) { 
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
