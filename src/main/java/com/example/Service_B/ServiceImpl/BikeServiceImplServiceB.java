package com.example.Service_B.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Git;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.Service_B.ServiceInter.BikeServiceInterServiceB;
import com.example.Service_B.dto.BikeDtoB;
import com.example.Service_B.entity.BikeEntityB;
import com.example.Service_B.repository.BikeRepositoryServiceB;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class BikeServiceImplServiceB implements BikeServiceInterServiceB {
	
	@Autowired
	BikeRepositoryServiceB bikeRepositoryServiceB;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BikeDtoB saveBikeB(BikeDtoB bikeDtoB) {
		
		log.info("Starting to save bike in DB: {}", bikeDtoB);

		bikeRepositoryServiceB.save(convertDtoToEntity(bikeDtoB));
		
		log.info("Bike saved in DB: {}", bikeDtoB);

		ResponseEntity<BikeDtoB> response = restTemplate.exchange(
			    "http://service_a:8082/saveBikeA",
			    HttpMethod.POST,
			    bikeDtoB,
			    BikeDtoB.class
			);		
		log.info("Response received from Service A: {}", response);

		return response.getBody();
	}
	public BikeDtoB convertEntityToDto(BikeEntityB bikeEntityB) {
		return modelMapper.map(bikeEntityB, BikeDtoB.class);
	}
	
	public BikeEntityB convertDtoToEntity(BikeDtoB bikeDtoB) {
		return modelMapper.map(bikeDtoB, BikeEntityB.class);
	}


}
