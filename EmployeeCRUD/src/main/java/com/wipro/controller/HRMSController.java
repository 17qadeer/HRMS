package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipro.entity.Employee;
import com.wipro.service.HRMSService;

@Controller
public class HRMSController {

	@Autowired
	private HRMSService hrmsService;
	

	@GetMapping("/")
	public String getInfo() {
		return "Main";

	}
	@GetMapping("/home")
	public String getHome() {
		return "EmployeeHome";
	}

	@GetMapping("/employee")
	public String showEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "Employee";
	}

	@PostMapping("/employee")
	public String submitEmployeeForm(@ModelAttribute Employee employee) {
		Employee saveEmployee = hrmsService.saveEmployee(employee);
		System.out.println(saveEmployee);
		return "Login";
	}

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("employee", new Employee());
		return "Login";
	}

	@PostMapping("/login")
	public String performLogin(@ModelAttribute Employee employee, Model model) {

		System.out.println(employee);

		if (hrmsService.loginUser(employee)) {
			Employee findByEmail = hrmsService.findByEmail(employee.getEmail());
			model.addAttribute("emp", findByEmail);
			
			return "EmployeeDetails";
		}

		return "Fail";
	}

	@GetMapping("/findByFirstName")
	public String findByFirstName(@RequestParam String firstName, Model model) {
		Employee findByName = hrmsService.findByName(firstName);
		System.out.println(findByName);
		model.addAttribute("emp", findByName);
		return "EmployeeDetails";
	}
	
	@GetMapping("/forgotPassword")
	public String getForgotPassword() {
		
		return "ForgotPassword";
	}
	
	@PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model) {
        
        
		Employee employee = hrmsService.findByEmail(email);
		
		if(employee == null) {
			model.addAttribute("error","Employee not found");
			return "ForgotPassword";
		}
		
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "ForgotPassword"; // return to the reset password page with error message
        }
        
        hrmsService.resetPassword(employee, confirmPassword);
        

        return "forgotPasswordSucessFull";
    }
	
	@ResponseBody
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees(){
		
		return hrmsService.getAll();
	}
	
	
}
