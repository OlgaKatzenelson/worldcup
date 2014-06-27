package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Game;
import com.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;

	public void save(Game game) { 
		repository.save(game);
		
	}

	public List<Game> findAll() {  
		return repository.findAll();
	}

	public List<Game> findByCountry(String countryName) { 
		return repository.findByCountry(countryName);
	}

	public List<Game> findByCountryAndIntDate(String country, Long start,Long finish) {
		return repository.findByCountryAndIntDate(country, start, finish);
	}

	public void drop() { 
		repository.deleteAll();
		
	} 
}
