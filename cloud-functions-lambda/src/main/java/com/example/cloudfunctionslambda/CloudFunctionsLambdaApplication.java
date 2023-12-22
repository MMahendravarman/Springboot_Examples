package com.example.cloudfunctionslambda;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudFunctionsLambdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudFunctionsLambdaApplication.class, args);
	}
	
	
	@Bean
	public Function<String, String> uppercase() {
		return value -> value.toUpperCase();
	}

}
