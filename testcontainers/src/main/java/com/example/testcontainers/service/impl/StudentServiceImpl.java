package com.example.testcontainers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.testcontainers.entity.Student;
import com.example.testcontainers.repository.StudentRepository;
import com.example.testcontainers.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	
	@Autowired
	StudentRepository repository;

	public Student addStudent(Student student) {
		return repository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return repository.findAll();
	}

}
