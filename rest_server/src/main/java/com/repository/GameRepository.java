package com.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Game;

@Repository
public interface GameRepository extends MongoRepository<Game,String>{

	List<Game> findByCountry(String countryName);

	@Query("{'country':?0, 'dateInt': {'$gt': ?1, '$lte': ?2}}")
	List<Game> findByCountryAndIntDate(String country, Long start, Long finish);   

//	List<Pie> findByLatAndLon(double lat, double lng);  

}