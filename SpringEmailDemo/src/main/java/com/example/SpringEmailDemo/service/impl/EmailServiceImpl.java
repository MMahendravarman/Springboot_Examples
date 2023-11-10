package com.example.SpringEmailDemo.service.impl;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.SpringEmailDemo.model.EmailConfig;
import com.example.SpringEmailDemo.service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;


	public void sendEmail(EmailConfig emailConfig) {
		MimeMessage mineMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper;
		
		try {
			mimeMessageHelper = new MimeMessageHelper(mineMessage,true);
			mimeMessageHelper.setFrom(emailConfig.getSender());
			mimeMessageHelper.setTo(emailConfig.getRecipients());
			mimeMessageHelper.setText(emailConfig.getBody());
			mimeMessageHelper.setSubject(emailConfig.getSubject());
			
			String fileName="D://1.txt";
			
			FileSystemResource file = new FileSystemResource(new File(fileName));
			Path path = Paths.get(fileName);
			byte[] contents = Files.readAllBytes(path);
			mimeMessageHelper.addAttachment(file.getFilename(), new ByteArrayResource(contents));
			
			javaMailSender.send(mineMessage);
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
