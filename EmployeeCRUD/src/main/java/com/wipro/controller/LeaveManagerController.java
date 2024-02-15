package com.wipro.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.entity.Employee;
import com.wipro.service.EmployeeCRUDService;
import com.wipro.service.HRMSService;
import com.wipro.service.LeavesService;

@Controller
public class LeaveManagerController {
	@Autowired
	private HRMSService hrmsService;

	@Autowired
	private LeavesService leavesService;

	@Autowired
	private EmployeeCRUDService employeeCrudService;

	@GetMapping("/applyLeaves/{id}")
	public String getLeaves(@PathVariable Long id, Model model) {

		int noOfLeaves = employeeCrudService.getLeaves(id);
		model.addAttribute("leaves", noOfLeaves);
		Employee employee = employeeCrudService.findById(id);
		model.addAttribute("emp", employee);
		return "LeaveManager";
	}

	@PostMapping("/applyLeaves/{id}")
	public String applyLeave(@PathVariable Long id, @RequestParam("startDate") String startDateStr,
			@RequestParam("endDate") String endDateStr, Model model) {

		LocalDate startDate = LocalDate.parse(startDateStr);
		LocalDate endDate = LocalDate.parse(endDateStr);

		System.out.println("start " + startDate);
		System.out.println("end " + endDate);
		long numberOfDays = leavesService.calculateNumberOfDays(startDate, endDate);
		
		System.out.println("No of days : " + numberOfDays);
		if(numberOfDays==0) {
			model.addAttribute("leaveUpdate","Invalid Selection of Start of and End Dates");
			Employee findById = employeeCrudService.findById(id);
			model.addAttribute("emp", findById);

			return "LeaveUpdateMessage";
		}

		String applyLeave = leavesService.applyLeave(id, numberOfDays);
		model.addAttribute("leaveUpdate", applyLeave);
		Employee findById = employeeCrudService.findById(id);
		model.addAttribute("emp", findById);

		return "LeaveUpdateMessage";
	}
	
	@GetMapping("/getPolicies")
	public String getPolicies() {
		
		return "Policies";
		
	}

}
