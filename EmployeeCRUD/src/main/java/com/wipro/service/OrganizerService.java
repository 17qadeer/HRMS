package com.wipro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Employee;
import com.wipro.entity.Job;
import com.wipro.entity.JobApplications;
import com.wipro.entity.Organization;
import com.wipro.service.repo.JobApplicationDao;
import com.wipro.service.repo.OrganizerRepo;

@Service
public class OrganizerService {

	@Autowired
	private OrganizerRepo organizerRepo;
	@Autowired
	private JobApplicationDao jobApplicationDao;
	@Autowired
	private EmployeeCRUDService employeeCRUDService;
	@Autowired
	private JobService jobService;

	public Organization saveOrganizer(Organization organization) {

		organization.setEmail(organization.getFirstName() + "." + organization.getLastName() + "@xyz.com");
		organization.setPassword(organization.getFirstName() + organization.getFirstName().length());

		return organizerRepo.save(organization);
	}

	public boolean loginOrganizer(Organization organization) {

		List<Organization> findAll = organizerRepo.findAll();

		for (Organization org : findAll) {
			if (org.getEmail().equals(organization.getEmail()) && org.getPassword().equals(organization.getPassword()))

				return true;
		}

		return false;
	}

	public Organization findByEmail(String email) {
		return organizerRepo.findByEmail(email);
	}

	public Organization findById(Long id) {
		return organizerRepo.findById(id).orElse(null);
	}

	public Organization updateOrganizer(Organization organization) {

		Organization findById = findById(organization.getId());

		findById.setFirstName(organization.getFirstName());
		findById.setLastName(organization.getLastName());
		findById.setGender(organization.getGender());
		findById.setRole(organization.getRole());
		findById.setNumber(organization.getNumber());
		findById.setEmail(organization.getFirstName() + "." + organization.getLastName() + "@xyz.com");
		findById.setPassword(organization.getFirstName() + organization.getFirstName().length());

		return organizerRepo.save(findById);

	}

	public List<Organization> getAll() {
		return organizerRepo.findAll();
	}
//ADDING JOB APPLICATION DATA TO DATABASE

	public String addJobApplication(JobApplications application) {
		jobApplicationDao.save(application);
		return "Job application saved successfully";
	}

	// Fetching JOB APPLICATIONS FROM DATABASE
	public List<JobApplications> fetchJobApplication() {
		return jobApplicationDao.findAll();
	}

	// UPDATING APPLICATION STATUS
	public String updateApplicationStatus(int applicationId, String status) {
		Optional<JobApplications> obj = jobApplicationDao.findById(applicationId);

		JobApplications updateObj = obj.get();
		System.out.println(updateObj);
		System.out.println(status);
		if (status.equals("accept")) {
			updateObj.setStatus("Accepted");
			Employee emp = employeeCRUDService.findById(updateObj.getEmployeeId());
			Job jobObj = jobService.fetchJobDetails(updateObj.getJobId());
			emp.setRole(jobObj.getTitle());
		} else if (status.equals("reject")) {
			updateObj.setStatus("Rejected");
		}
		jobApplicationDao.save(updateObj);
		return "Successfully update application status";
	}
}
