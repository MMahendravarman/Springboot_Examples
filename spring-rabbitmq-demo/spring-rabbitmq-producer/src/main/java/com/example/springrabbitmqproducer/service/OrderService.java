package com.example.springrabbitmqproducer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springrabbitmqproducer.model.OrderRequest;
import com.example.springrabbitmqproducer.model.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderService {

	@Autowired
	RabbitTemplate template;
	
	public ResponseEntity<OrderResponse> placeOrder(OrderRequest request) throws JsonProcessingException{		
		//Add logic to save the order to DB		
		//Notify downward systems for fulfilment and payment
		template.convertAndSend("x.order-notification","",request);		
		OrderResponse resp = new OrderResponse();
		resp.setMessage("Order Created");
		resp.setStatus(201);
		return new ResponseEntity<OrderResponse>(resp, HttpStatusCode.valueOf(201));
	}
}
