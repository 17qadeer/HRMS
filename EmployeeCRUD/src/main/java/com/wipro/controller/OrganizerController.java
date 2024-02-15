package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipro.entity.Employee;
import com.wipro.entity.JobApplications;
import com.wipro.entity.Organization;
import com.wipro.service.HRMSService;
import com.wipro.service.OrganizerService;

@Controller
public class OrganizerController {

	@Autowired
	private OrganizerService organizerService;
	@Autowired
	private HRMSService hrmsService;

	@GetMapping("/OrganizationHome")
	public String getOrganizationHome() {
		return "OrganizationHome";
	}

	@GetMapping("/getOrganizer")
	public String getInfo() {
		return "OrganizationHome";

	}

	@GetMapping("/organizer")
	public String showOrganizerForm(Model model) {
		model.addAttribute("org", new Organization());
		return "Organization";
	}

	@PostMapping("/organizer")
	public String submitOrganizerForm(@ModelAttribute("org") Organization organization) {
		Organization saveOrganizer = organizerService.saveOrganizer(organization);
		System.out.println(saveOrganizer);
		return "OrganizationHome";
	}

	@GetMapping("/loginOrganizer")
	public String getOrganizerLogin(Model model) {
		model.addAttribute("org", new Organization());
		return "OrganizationLogin";
	}

	@PostMapping("/loginOrganizer")
	public String performLogin(@ModelAttribute("org") Organization organization, Model model) {

		System.out.println(organization);

		if (organizerService.loginOrganizer(organization)) {
			Organization findByEmail = organizerService.findByEmail(organization.getEmail());
			model.addAttribute("org", findByEmail);
			return "OrganizerDetails";
		}

		return "Fail";
	}

	@GetMapping("/editOrganizer/{id}")
	public String editOrganizer(Model model, @PathVariable String id) {
		Long unique = Long.parseLong(id);
		Organization findById = organizerService.findById(unique);
		model.addAttribute("org", findById);
		return "EditOrganisation";
	}

	@PostMapping("/updateOrganizer")
	public String updateEmployee(@ModelAttribute("org") Organization organization, Model model) {
		System.out.println(organization);
		organizerService.updateOrganizer(organization);
		model.addAttribute("message", "Sucessfully Updated the Organizer Details Kindly login again");
		return "OrganisationSuccess";
	}

	@GetMapping("/getOrganisationChart")
	public String getOrganisationMemebers(Model model) {
		List<Organization> organizers = organizerService.getAll();
		List<Employee> employees = hrmsService.getAll();
		model.addAttribute("orgs", organizers);
		model.addAttribute("emps", employees);

		return "CompanyChart";
	}

	@GetMapping("/organizer/{id}")
	public String getParticularOrganizerDetails(@PathVariable Long id, Model model) {
		Organization organizer = organizerService.findById(id);
		model.addAttribute("org", organizer);

		return "particularOrganizer";
	}

	// GETTING DATA FROM EMPLOYEE DASHBORAD FOR JOB APPLICATIONS
	@GetMapping("/saveApplications/{empIdF}/{jobId}")
	public String fetchApplications(@PathVariable Long empIdF, @PathVariable Long jobId, Model model) {
		// Creating Application Object
		JobApplications application = new JobApplications();
		// Setting 3 fields
		application.setEmployeeId(empIdF);
		application.setJobId(jobId);
		application.setStatus("InProgress");
		// Saving Application to Database
		organizerService.addJobApplication(application);
		return "SuccessAfterJobApplying";
	}

	// Fetching Applications Data
	@GetMapping("/fetchApplications")
	public String fetchAllApplications(Model model) {
		List<JobApplications> ApplicationList = organizerService.fetchJobApplication();
		model.addAttribute("List", ApplicationList);
		return "JobApplications";
	}

	// ACCEPTING AND REJECTING THE APPLICATION
	@GetMapping("/Action/{applicationId}/{action}")
	public String acceptOrRejectApplication(@PathVariable int applicationId, @PathVariable String action, Model model) {
		organizerService.updateApplicationStatus(applicationId, action);
		List<JobApplications> ApplicationList = organizerService.fetchJobApplication();
		model.addAttribute("List", ApplicationList);
		return "JobApplications";
	}

	@ResponseBody
	@GetMapping("/getAllOrganizers")
	public List<Organization> getAll() {
		return organizerService.getAll();
	}

}
