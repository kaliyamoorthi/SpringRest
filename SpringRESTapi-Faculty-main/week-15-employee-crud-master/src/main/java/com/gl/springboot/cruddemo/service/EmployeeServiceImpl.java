package com.gl.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.springboot.cruddemo.dao.EmployeeRepository;
import com.gl.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee = null;
		
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new IllegalArgumentException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public Employee findByFirstNameAndLastName(String firstName, String lastName) {
		return this.employeeRepository.findByFirstNameAndLastName(firstName, lastName)
				.orElseThrow(() -> new IllegalArgumentException("Invalid first name or last Name"));
	}
	
	@Override
	public Employee findByFirstNameOrLastName(String firstName, String lastName) {
		return this.employeeRepository.findByFirstNameOrLastName(firstName, lastName)
				.orElseThrow(() -> new IllegalArgumentException("Invalid first name or last Name"));
	}

	@Override
	public Employee findByEmail(String emailAddress) {
		return this.employeeRepository.findByEmail(emailAddress)
				.orElseThrow(() -> new IllegalArgumentException("Invalid first name or last Name"));
	}

}






