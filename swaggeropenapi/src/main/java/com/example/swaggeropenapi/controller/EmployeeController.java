package com.example.swaggeropenapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swaggeropenapi.entity.Employee;
import com.example.swaggeropenapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Employee", description = "the Employee API")
public class EmployeeController {

	@Autowired
	private EmployeeService svc;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return svc.getall();
		
	}
	
	@Operation(summary = "To add an employee", tags = { "Employee" })
	@ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = Employee.class)) }) })
	@PostMapping(value="employee/add",consumes = { "application/json" })
	public Employee addEmp(@RequestBody Employee emp) {
		return svc.add(emp);
		
			
	}
}
