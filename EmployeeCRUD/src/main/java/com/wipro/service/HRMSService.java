package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Employee;
import com.wipro.service.repo.HRMSRepo;

@Service
public class HRMSService {

	@Autowired
	private HRMSRepo hrmsRepo;

	public Employee saveEmployee(Employee employee) {

		employee.setEmail(employee.getFirstName() + "." + employee.getLastName() + "@xyz.com");
		employee.setPassword(employee.getFirstName() + employee.getFirstName().length());
		employee.setLeaves(15);
		return hrmsRepo.save(employee);
	}

	public boolean loginUser(Employee employee) {

		List<Employee> allEmployees = hrmsRepo.findAll();

		for (Employee emp : allEmployees) {
			if (emp.getEmail().equals(employee.getEmail()) && emp.getPassword().equals(employee.getPassword()))

				return true;
		}

		return false;
	}

	public Employee findByName(String firstName) {

		Employee findByFirstName = hrmsRepo.findByFirstName(firstName);

		return findByFirstName;
	}

	public Employee findByEmail(String email) {
		return hrmsRepo.findByEmail(email);
	}

	public List<Employee> getAll() {
		return hrmsRepo.findAll();
	}

	public void resetPassword(Employee employee, String password) {
		employee.setPassword(password);
		hrmsRepo.save(employee);
		
	}
	
	

}
