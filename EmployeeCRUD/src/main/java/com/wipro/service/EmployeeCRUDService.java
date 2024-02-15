package com.wipro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Employee;
import com.wipro.service.repo.HRMSRepo;

@Service
public class EmployeeCRUDService {
	@Autowired
	private HRMSRepo hrmsRepo;

	public Employee findById(Long id) {
		return hrmsRepo.findById(id).orElse(null);
	}

	public Employee updateEmployee(Employee employee) {

		Employee findById = findById(employee.getId());

		findById.setFirstName(employee.getFirstName());
		findById.setLastName(employee.getLastName());
		findById.setGender(employee.getGender());
		findById.setRole(employee.getRole());
		findById.setNumber(employee.getNumber());
		findById.setEmail(employee.getFirstName() + "." + employee.getLastName() + "@xyz.com");
		//findById.setPassword(employee.getFirstName() + employee.getFirstName().length());

		hrmsRepo.save(findById);
		System.out.println(findById);
		return null;

	}

	public int getLeaves(Long id) {
		Employee emp = hrmsRepo.findById(id).orElse(null);
		return emp.getLeaves();
	}
	
	
	public String updatePassword(String newPassword,Long id ) {
		
	Employee employee = hrmsRepo.findById(id).orElse(null);
	employee.setPassword(newPassword);
	hrmsRepo.save(employee);
	return "Password Set Successfully";
		
	}

}
