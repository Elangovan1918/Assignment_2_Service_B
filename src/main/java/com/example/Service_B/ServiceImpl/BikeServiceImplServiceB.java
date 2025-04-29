package com.example.Service_B.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

		BikeDtoB bikeResponse =restTemplate.postForEntity("http://localhost:8885/saveBikeA", bikeDtoB, BikeDtoB.class).getBody();
		
		log.info("Response received from Service A: {}", bikeResponse);

		return bikeResponse;
	}
	
	public BikeDtoB convertEntityToDto(BikeEntityB bikeEntityB) {
		return modelMapper.map(bikeEntityB, BikeDtoB.class);
	}
	
	public BikeEntityB convertDtoToEntity(BikeDtoB bikeDtoB) {
		return modelMapper.map(bikeDtoB, BikeEntityB.class);
	}


}
