package com.gl.springboot.cruddemo.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;
import com.gl.springboot.cruddemo.dao.EmployeeRepository;
import com.gl.springboot.cruddemo.entity.Address;
import com.gl.springboot.cruddemo.entity.Employee;

@Configuration
public class BootstrapApplicationConfig implements ApplicationListener<ApplicationReadyEvent>{
	
	private EmployeeRepository employeeRepository;
	private final Faker faker = new Faker();
	
	public BootstrapApplicationConfig(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		for(int index = 0; index < 100; index ++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String domainName = faker.internet().domainName();
			
			Employee employee = new Employee(firstName, lastName, firstName+"."+lastName+ "@"+domainName);
			
			for(int j = 0; j < 2; j++) {
				String city = faker.address().cityName();
				String zipCode = faker.address().zipCode();
				String street = faker.address().streetAddress();
				Address address = new Address(street, city, zipCode);
				employee.addAddress(address);
			}
			this.employeeRepository.save(employee);
		}
		
	}

}
