package com.example.SpringEmailDemo.service;

import org.springframework.stereotype.Service;

import com.example.SpringEmailDemo.model.EmailConfig;

@Service
public interface EmailService {

	public void sendEmail(EmailConfig emailConfig);
}
