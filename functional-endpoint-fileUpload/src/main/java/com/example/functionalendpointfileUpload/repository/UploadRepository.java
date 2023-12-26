package com.example.functionalendpointfileUpload.repository;

import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;

import com.example.functionalendpointfileUpload.entity.Employee;

public interface UploadRepository extends ReactiveCouchbaseRepository<Employee, String>{

}
