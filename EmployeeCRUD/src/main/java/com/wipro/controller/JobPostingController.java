package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipro.entity.Employee;
import com.wipro.entity.Job;
import com.wipro.entity.JobApplications;
import com.wipro.service.EmployeeCRUDService;
import com.wipro.service.JobService;

@Controller
public class JobPostingController {
 
	@Autowired
	private JobService jobService;
	@Autowired
	private EmployeeCRUDService empService;
 
	@GetMapping("/JobsAvailable/{id}")
	public String display(Model model,@PathVariable Long id) {
		System.out.println("fetch id:"+id);
		Employee fetchEmp=empService.findById(id);
		List<Job> allJobs = jobService.getAllJobs();
		model.addAttribute("jobs", allJobs);
		model.addAttribute("empId",fetchEmp.getId());
		if(fetchEmp.getRole().equals("freepool")) {
			return "JobsAvailableForFreepool";
		}else {
			return "JobsAvailable";
		}
	}
 
	@PostMapping("/postjob")
	@ResponseBody
	public Job addJob(@RequestBody Job job) {
		return jobService.addJob(job);
	}
	//FETCHING EMPLOYEE APPLICATION STATUS
	@GetMapping("/FetchEmployeeApplications/{empId}")
	public String fetchApplications(@PathVariable Long empId,Model model) {
		List<JobApplications> applicationList= jobService.fetchApplicationsOfEmployee(empId);
		model.addAttribute("List",applicationList);
		return"EmployeeApplications";
	}
 
 
}
