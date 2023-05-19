package com.example.swaggeropenapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.swaggeropenapi.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}
