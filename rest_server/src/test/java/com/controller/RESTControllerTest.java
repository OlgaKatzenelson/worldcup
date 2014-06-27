package com.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.WebMainClass;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebMainClass.class)
@EnableMongoRepositories("com.repository")
@WebAppConfiguration
@Ignore
public class RESTControllerTest {
	@Test
    public void findAllPieInfo() {
      RestTemplate template = new RestTemplate();
      ResponseEntity<String> response2 = template.getForEntity("http://localhost:8080/pie", String.class);
    }
}
