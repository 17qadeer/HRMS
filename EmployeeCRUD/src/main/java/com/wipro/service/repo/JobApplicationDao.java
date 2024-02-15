package com.wipro.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.JobApplications;

public interface JobApplicationDao extends JpaRepository<JobApplications, Integer> {

	// Method to fetch job applications by employee ID
	List<JobApplications> findByEmployeeId(Long employeeId);
}
