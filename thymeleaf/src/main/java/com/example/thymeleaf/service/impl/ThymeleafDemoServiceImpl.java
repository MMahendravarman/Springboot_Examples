package com.example.thymeleaf.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.model.Employee;
import com.example.thymeleaf.service.ThymeleafDemoService;
import com.example.thymeleaf.util.ExcelReader;

@Service
public class ThymeleafDemoServiceImpl implements ThymeleafDemoService{
	@Autowired
	ExcelReader excelReader;	
	public List<Employee> getEmployees() throws IOException {
		
		return excelReader.readExcel();
	}
}
