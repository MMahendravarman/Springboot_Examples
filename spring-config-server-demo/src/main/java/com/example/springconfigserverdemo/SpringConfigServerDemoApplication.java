package com.example.springconfigserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringConfigServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigServerDemoApplication.class, args);
	}

}
