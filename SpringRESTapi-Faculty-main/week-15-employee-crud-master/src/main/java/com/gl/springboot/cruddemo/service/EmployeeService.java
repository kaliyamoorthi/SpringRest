package com.gl.springboot.cruddemo.service;

import java.util.List;

import com.gl.springboot.cruddemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	public Employee findByFirstNameAndLastName(String firstName, String lastName);
	
	public Employee findByFirstNameOrLastName(String firstName, String lastName);
	
	public Employee findByEmail(String emailAddress);
	
}
