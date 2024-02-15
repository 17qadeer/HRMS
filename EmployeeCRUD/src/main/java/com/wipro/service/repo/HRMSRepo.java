package com.wipro.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.Employee;

public interface HRMSRepo extends JpaRepository<Employee, Long> {

	public Employee findByFirstName(String firstName);
	public Employee findByLastName(String lastName);
	public Employee findByEmail(String email);
}
