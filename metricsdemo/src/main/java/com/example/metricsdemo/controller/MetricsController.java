package com.example.metricsdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

	@GetMapping("/greet/{name}")
	public String greet(@PathVariable String name) {
		return "Hello " + name;
	}
}
