package com.repository;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@PropertySource("classpath:application.properties")
@EnableMongoRepositories("com.repository")
public class MongoRepository {
	@Value("#{environment.mongo_replica_set_read}")
	private String readReplicaSet;

	@Value("#{environment.mongo_read_schema}")
	private String readSchema;
	@Value("#{environment.mongo_username}")
	private String username;
	@Value("#{environment.mongo_pass}")
	private String password;

	@Bean(name="mongoTemplate") 
	public MongoTemplate getReadMongoTemplate() throws UnknownHostException {   
		
		MongoClient mongoClient = setSingleHost(readReplicaSet); 
		UserCredentials uc = new UserCredentials(username, password);
		return new MongoTemplate(mongoClient, readSchema, uc);
	}
	
	private MongoClient setSingleHost(String replica) throws UnknownHostException {
		MongoClient mongoClient;
		String[] addressParts = replica.split(":"); 
		Integer port = Integer.valueOf(addressParts[1].trim());
		mongoClient = new MongoClient(addressParts[0].trim(), port);
		return mongoClient;
	}
}
