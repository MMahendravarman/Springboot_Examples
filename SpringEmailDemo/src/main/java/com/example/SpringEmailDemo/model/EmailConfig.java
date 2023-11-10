package com.example.SpringEmailDemo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties("emailconfig")
public class EmailConfig {
	
	private String recipients;
	private String sender;
	private String body;
	private String subject;

}
