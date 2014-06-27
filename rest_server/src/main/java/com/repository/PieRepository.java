package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Pie;

@Repository
public interface PieRepository extends MongoRepository<Pie,String>{ 

//	List<Pie> findByLatAndLon(double lat, double lng);  

}