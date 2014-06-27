package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Pie; 
import com.repository.PieRepository;

@Service
public class PieService {

	@Autowired
	private PieRepository pieRepository;

	public void save(Pie pie) { 
		pieRepository.save(pie);
		
	}

	public List<Pie> findAll() {  
		return pieRepository.findAll();
	}

	public void drop() { 
		pieRepository.deleteAll();	
	} 
	
	/*
	 * Round a lat and lng to 4 decimal places
	 */
//	public void insertPlace(Place place) {    
//		place.setTimestamp(System.currentTimeMillis());
//		try {
//			Place clone = (Place) place.clone();
//			clone.setLat(Double.valueOf(df.format(clone.getLat())));
//			clone.setLon(Double.valueOf(df.format(clone.getLon())));
//			geoRepository.save(clone);
//		} catch (CloneNotSupportedException e) {
//			LOGGER.error("Error during Place clonning: ", e);
//		}   
//		
//	}   
}
