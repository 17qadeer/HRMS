package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Job;
import com.wipro.entity.JobApplications;
import com.wipro.service.repo.JobApplicationDao;
import com.wipro.service.repo.JobRepo;

@Service
public class JobService {
	@Autowired
	private JobRepo jobRepo;
    @Autowired
	private JobApplicationDao jobApplicationDao;
 
	public Job addJob(Job job) {
		return jobRepo.save(job);
 
	}
 
	public List<Job> getAllJobs() {
		return jobRepo.findAll();
	}
 
	// FETCHING JOB APPLICATIONS OF SPECIFIC EMPLOYEE
	public List<JobApplications> fetchApplicationsOfEmployee(Long empId) {
		List<JobApplications> applicationList = jobApplicationDao.findByEmployeeId(empId);
		return applicationList;
	}
	
	//FETCHING JOB DETAILS ON BASE OF JOB ID
	public Job fetchJobDetails(Long id) {
		Optional<Job> job=jobRepo.findById(id);
		Job JobObject=job.get();
		return JobObject;
		
	}

}
