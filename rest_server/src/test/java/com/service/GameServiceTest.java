package com.service;

import java.util.Calendar;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebMainClass.class)
@EnableMongoRepositories("com.repository")
@WebAppConfiguration
public class GameServiceTest{
	@Autowired
	private GameService service;  

	@Test
	public void insertTest(){	
		service.drop();
		service.save(new Game(Game.Country.GERMANY.name().toLowerCase(), "Germany is the best","Alex", 20140624134622L)); 
		service.save(new Game(Game.Country.GERMANY.name().toLowerCase(), "Germany will win","Winner", 20140624134722L)); 
		service.save(new Game(Game.Country.BRAZIL.name().toLowerCase(), "I love Brazil","Roy", 20140624133022L)); 

		List<Game> res = service.findByCountryAndIntDate(Game.Country.GERMANY.name().toLowerCase(), 20140623134622L, 20140624134623L);
		Assert.assertTrue(res.size() == 1);


		Calendar.getInstance().getTime();
		long time = 20140624133022L;
		for (int i=0; i< 50; i++) {

			if(i%2 == 0){
				service.save(new Game(Game.Country.BRAZIL.name().toLowerCase(), "I love Brazil","Roy", time)); 

				try {
					Thread.sleep(3000);
					time+=3;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				service.save(new Game(Game.Country.GERMANY.name().toLowerCase(), "Germany is the best","Alex", time)); 
				try {
					Thread.sleep(6000);
					time+=6;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}


		}
	} 
}
