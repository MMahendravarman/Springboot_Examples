package com.example.springconfigclientdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringConfigClientController {
	
	@Value("${greeting}")
	String greeting;

	@GetMapping("/greet")
	public String greet() {
		
		return greeting;
	}
}
