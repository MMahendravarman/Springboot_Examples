package com.example.testcontainers.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.testcontainers.entity.Student;



@Service
public interface StudentService {


	Student addStudent(Student student);

	List<Student> getAllStudents();



}
