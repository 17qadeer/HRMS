package com.wipro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobApplications {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int applicationId;
	private Long employeeId;
	private Long jobId;
	private String status;

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JobApplications() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobApplications(int applicationId, Long employeeId, Long jobId, String status) {
		super();
		this.applicationId = applicationId;
		this.employeeId = employeeId;
		this.jobId = jobId;
		this.status = status;
	}

	@Override
	public String toString() {
		return "JobApplications [applicationId=" + applicationId + ", employeeId=" + employeeId + ", jobId=" + jobId
				+ ", status=" + status + "]";
	}

}
