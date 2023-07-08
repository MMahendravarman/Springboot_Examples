package com.example.postgresdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.postgresdemo.entity.Student;

@Service
public interface StudentService {

	Student addStudent(Student student);

	List<Student> getAllStudents();

}
