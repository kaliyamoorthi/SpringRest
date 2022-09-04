package com.gl.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gl.springboot.cruddemo.entity.Employee;
import com.gl.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	// expose "/employees" and return list of employees
	/*
	 * @GetMapping("/employees") public List<Employee> findAll() { return
	 * employeeService.findAll(); }
	 */

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		return theEmployee;
	}
	
	//http://localhost:8222/api/employees/name?fname=Christopher&lname=Borer
	@GetMapping("/employees")
	public Object getEmployeeByName(
				@RequestParam(required = false) String fname,
				@RequestParam(required=false) String lname) {
		
		if(fname == null && lname == null) {
			return this.employeeService.findAll();
		} else if (fname == null || lname ==null) {
			return this.employeeService.findByFirstNameOrLastName(fname, lname);
		}
		
		return this.employeeService.findByFirstNameAndLastName(fname, lname);

	}
	
	// add mapping for POST /employees - add new employee
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee employee) {
		
		// very important to set the relationship
		employee.getAddresses().forEach(address -> address.setEmployee(employee));
		employeeService.save(employee);
		
		return employee;
	}
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	@DeleteMapping("/employees/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.findById(employeeId);
		
		// throw exception if null
		
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		employeeService.deleteById(employeeId);
	}
}










