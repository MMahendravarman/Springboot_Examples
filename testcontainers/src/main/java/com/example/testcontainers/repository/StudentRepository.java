package com.example.testcontainers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testcontainers.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
