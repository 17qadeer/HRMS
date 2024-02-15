package com.wipro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.entity.Employee;
import com.wipro.service.EmployeeCRUDService;
import com.wipro.service.HRMSService;

@Controller
public class EmployeeCrudController {

	@Autowired
	private EmployeeCRUDService employeeCrudService;

	@Autowired
	private HRMSService hrmsService;

	@GetMapping("/edit/{id}")
	public String editEmployee(Model model, @PathVariable String id) {
		Long employeeId = Long.parseLong(id);
		Employee findById = employeeCrudService.findById(employeeId);
		model.addAttribute("employee", findById);
		return "EditEmployee";
	}

	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee employee, Model model) {
		System.out.println(employee);
		employeeCrudService.updateEmployee(employee);
		model.addAttribute("message", "Sucessfully Updated the Details login again");
		return "Sucess";
	}

	@GetMapping("/employee/{id}")
	public String getParticularOrganizerDetails(@PathVariable Long id, Model model) {
		Employee employee = employeeCrudService.findById(id);
		model.addAttribute("emp", employee);

		return "particularEmployee";
	}

	@GetMapping("/resetPassword/{id}")
	public String resetPassword(@PathVariable Long id, Model model) {
		Employee employee = employeeCrudService.findById(id);
		model.addAttribute("emp", employee);
		return "ResetPassword";
	}

	@PostMapping("/resetPassword/{id}")
	public String resetPassword(@RequestParam String email, @RequestParam("password") String newPassword,
			@RequestParam String confirmPassword, Model model, @PathVariable Long id) {

		if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("error", "Passwords do not match");
			Employee findById = employeeCrudService.findById(id);
			model.addAttribute("emp", findById);
			return "ResetPassword"; // Return to the reset password page with error message
		}

		String result = employeeCrudService.updatePassword(newPassword, id);
		model.addAttribute("message", result);
		return "passwordResetSucces"; // Redirect to a success page
	}

	@GetMapping("/employee-details/{id}")
	public String getEmployeeDetails(@PathVariable Long id, Model model) {
		Employee employee = employeeCrudService.findById(id);
		model.addAttribute("emp", employee);

		return "EmployeeDetails";

	}

}
