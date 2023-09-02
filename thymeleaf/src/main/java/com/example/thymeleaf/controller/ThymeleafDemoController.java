package com.example.thymeleaf.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.thymeleaf.model.Employee;
import com.example.thymeleaf.service.ThymeleafDemoService;

@Controller
public class ThymeleafDemoController {
	
	@Autowired
	ThymeleafDemoService service;
	
	@GetMapping("/employees")
	public String displayExcelContents(Model model) {		
		List<Employee> employees = null;
		try {
			employees = service.getEmployees();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		model.addAttribute("employees", employees);		
		return "employees";
	}
}
