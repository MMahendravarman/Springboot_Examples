package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postgresdemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
