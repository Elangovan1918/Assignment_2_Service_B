package com.example.Service_B.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service_B.ServiceInter.BikeServiceInterServiceB;
import com.example.Service_B.dto.BikeDtoB;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class BikeControllerServiceB {
	
	@Autowired
	BikeServiceInterServiceB bikeServiceInterServiceB;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@PostMapping("/saveBikeB")
	public BikeDtoB saveBikeB(@RequestBody BikeDtoB bikeDtoB) {
		
		log.info("Received request to save bike: {}", bikeDtoB);

		bikeDtoB=bikeServiceInterServiceB.saveBikeB(bikeDtoB);
		
		log.info("Bike saved successfully: {}", bikeDtoB);

		return bikeDtoB;
	}

}
