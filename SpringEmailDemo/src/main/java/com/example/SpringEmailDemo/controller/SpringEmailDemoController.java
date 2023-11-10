package com.example.SpringEmailDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringEmailDemo.model.EmailConfig;
import com.example.SpringEmailDemo.service.EmailService;

@RestController
public class SpringEmailDemoController {

	@Autowired
	EmailConfig emailConfig;
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendEmail")
	public void sendEmail() {
		
		emailService.sendEmail(emailConfig);
	}
}
