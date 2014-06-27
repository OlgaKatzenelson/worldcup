package com.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.WebMainClass;
import com.model.Game;
import com.model.Pie;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebMainClass.class)
@EnableMongoRepositories("com.repository")
@WebAppConfiguration
public class PieServiceTest{
	@Autowired
	private PieService pieService;  

	@Test
	public void insertTest(){
		pieService.drop();
		pieService.save(new Pie("iPhone", 5)); 
		pieService.save(new Pie("Web", 8));
		pieService.save(new Pie("Desk", 12));
		pieService.save(new Pie("ftp", 1));
		
		List<Pie> res = pieService.findAll();
		Assert.assertTrue(res.size() == 4);
	
	} 
}
