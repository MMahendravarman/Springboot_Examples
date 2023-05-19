package com.example.swaggeropenapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swaggeropenapi.entity.Employee;
import com.example.swaggeropenapi.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;
	
	public List<Employee> getall() {
		// TODO Auto-generated method stub
		return (List<Employee>) repo.findAll();
	}

	public Employee add(Employee emp) {
		// TODO Auto-generated method stub
		return repo.save(emp);
	}

}
