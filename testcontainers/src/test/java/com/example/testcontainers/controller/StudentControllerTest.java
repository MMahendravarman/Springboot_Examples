package com.example.testcontainers.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.testcontainers.entity.Student;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Testcontainers
class StudentControllerTest {
	
	@LocalServerPort
    int randomServerPort;	

    @Autowired
    private TestRestTemplate restTemplate;
	
	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest");	

	@Test
	void testAdd() throws URISyntaxException {
		 	final String baseUrl = "http://localhost:"+randomServerPort+"/Student";
	        URI uri = new URI(baseUrl);
	        Student student = Student.builder().rollNumber(20230101).age(18).name("Mike").build();	         
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("X-COM-PERSIST", "true"); 
	        HttpEntity<Student> request = new HttpEntity<>(student, headers);	         
	        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);	         
	        assertEquals(200, result.getStatusCode().value());
	}

	@Test
	void testGetAll() throws URISyntaxException {
		final String baseUrl = "http://localhost:"+randomServerPort+"/Student";
        URI uri = new URI(baseUrl);
        ResponseEntity<Student[]> result = this.restTemplate.getForEntity(uri,Student[].class);     
        assertEquals(200, result.getStatusCode().value());
	}

}
