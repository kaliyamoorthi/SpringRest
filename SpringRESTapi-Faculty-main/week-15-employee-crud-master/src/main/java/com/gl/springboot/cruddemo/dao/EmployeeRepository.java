package com.gl.springboot.cruddemo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.springboot.cruddemo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Optional<Employee> findByEmail(String email);
	
	Optional<Employee>findByFirstNameAndLastName(String firstName, String lastName);
	
	Optional<Employee>findByFirstNameOrLastName(String firstName, String lastName);
}
