package com.example.testcontainers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Student")
@Builder
public class Student {
	
	@Id
	@Column(name="rollnumber")
	private int rollNumber;
	
	private String name;
	
	private int age;


}
