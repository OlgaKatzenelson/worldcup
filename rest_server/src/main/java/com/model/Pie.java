package com.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="topn")
public class Pie {

	private String source;
	private int count; 

	public Pie(String source, int count) { 
		this.source = source;
		this.count = count;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
