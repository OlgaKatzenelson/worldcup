package com.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="twit")
public class Game {

	public enum Country{GERMANY, BRAZIL}
	
	private String country;
	private String twit;
	private String userName;
	private long dateInt;
	
	public Game(){}
	
	public Game(String country, String twit, String userName, long dateInt) {
		this.country = country;
		this.twit = twit;
		this.dateInt = dateInt;
		this.setUserName(userName);
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTwit() {
		return twit;
	}
	public void setTwit(String twit) {
		this.twit = twit;
	}
	public long getDateInt() {
		return dateInt;
	}
	public void setDateInt(long dateInt) {
		this.dateInt = dateInt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
