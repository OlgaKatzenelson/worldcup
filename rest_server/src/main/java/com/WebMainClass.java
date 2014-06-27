package com;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.model.Tweet;

@ComponentScan
@EnableAutoConfiguration
@EnableWebMvc
public class WebMainClass {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(WebMainClass.class, args);

		//Test rest api
//		RestTemplate template = new RestTemplate();
//		ResponseEntity<String> response2 = template.getForEntity("http://localhost:8080/pie", String.class);
//		ResponseEntity<Tweet> response =  template.postForEntity("http://localhost:8080/save/twit","first twit", String.class, Collections.EMPTY_MAP);

	}

}
