package com.example.thymeleaf.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.thymeleaf.model.Employee;

@Service
public interface ThymeleafDemoService {

	public List<Employee> getEmployees() throws IOException;
}
