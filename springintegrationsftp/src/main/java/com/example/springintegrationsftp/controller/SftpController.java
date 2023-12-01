package com.example.springintegrationsftp.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springintegrationsftp.service.SftpService;

@RestController
public class SftpController {
	
	@Autowired
	SftpService service;

	@GetMapping("/sendFile")
	public void sendFile() {
	
		service.uploadFileToSftp();
	}
}
