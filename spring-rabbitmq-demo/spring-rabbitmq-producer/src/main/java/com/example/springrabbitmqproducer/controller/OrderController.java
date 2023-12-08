package com.example.springrabbitmqproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.springrabbitmqproducer.model.OrderRequest;
import com.example.springrabbitmqproducer.model.OrderResponse;
import com.example.springrabbitmqproducer.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class OrderController {

	@Autowired
	OrderService service;	
	
	@PostMapping("/order")
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) throws JsonProcessingException{		
		
		return service.placeOrder(request);
		
	}
}
