package com.example.postgresdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.entity.Student;
import com.example.postgresdemo.service.StudentService;

@RestController
public class PostgresdemoController {
	
	@Autowired
	StudentService service;
	
	@PostMapping("/Student")
	public Student add(@RequestBody Student student) {
		return service.addStudent(student);
		
	}
	
	@GetMapping("/Student")
	public List<Student> getAll() {
		return service.getAllStudents();
		
	}

}
