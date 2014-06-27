package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Game;
import com.model.Pie;
import com.model.Tweet;
import com.repository.TweetRepository;
import com.service.GameService;
import com.service.PieService;

@RestController
public class RESTController {      
	@Autowired
	private PieService pieService;

	@Autowired
	private GameService gameService;

	@Autowired
	private TweetRepository twitRepository;

	@RequestMapping(value="/pie", method=RequestMethod.GET)
	public @ResponseBody List<Pie> providePieInfo() {  
		return pieService.findAll();
	}

	@RequestMapping(value="/game/germany", method=RequestMethod.GET)
	public @ResponseBody List<Game> provideGermanyInfo(@RequestParam(value="start", required=false) Long start, @RequestParam(value="finish", required=false) Long finish) {  
		if(start!= null && finish != null){
			return gameService.findByCountryAndIntDate("germany", start, finish); 
		}
		return gameService.findByCountry("germany"); 
	}

	@RequestMapping(value="/game/brasil", method=RequestMethod.GET)
	public @ResponseBody List<Game> provideBrazilInfo(@RequestParam(value="start", required=false) Long start, @RequestParam(value="finish", required=false) Long finish) {  
		if(start!= null && finish != null){
			return gameService.findByCountryAndIntDate("brasil", start, finish); 
		}
		return gameService.findByCountry("brasil");
	}
	
	@RequestMapping(value="/game/empty", method=RequestMethod.GET)
	public @ResponseBody List<Game> provideEmptyInfo() {  
		return gameService.findByCountry("");
	}

	@RequestMapping(value="/save/tweet", method=RequestMethod.POST)
	public Tweet saveTwit(@RequestBody @Valid final String message) {  
		Tweet tweet = new Tweet(message);
		return twitRepository.save(tweet); 
	}
}