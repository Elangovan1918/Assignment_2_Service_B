package com.example.Service_B.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.Service_B.dto.BikeDtoB;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BikeConsumer {
	
	@Autowired
	ObjectMapper objectMapper;

	@KafkaListener(topics = "BikeService",groupId = "My_Group")
	public void saveBike(String bikeDto) {
		
		log.info("Received message from Kafka topic 'BikeService': {}", bikeDto);

		try {
			BikeDtoB bikeDtoB=objectMapper.readValue(bikeDto, BikeDtoB.class);
			
			log.info("Successfully deserialized Kafka message to BikeDtoB: {}", bikeDtoB);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
