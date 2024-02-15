package com.wipro.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.entity.Employee;
import com.wipro.entity.EmployeeInOutTable;
import com.wipro.service.EmployeeCRUDService;
import com.wipro.service.EmployeeCheckInOut;

@Controller
public class EmployeeTimeController {

	@Autowired
	private EmployeeCheckInOut employeeCheckInOutService;

	@Autowired
	private EmployeeCRUDService crudService;

	@GetMapping("/markTimeSheet/{id}")
	public String getTimeSheet(@PathVariable Long id, Model model) {
		Employee employee = crudService.findById(id);
		model.addAttribute("emp", employee);
		return "TimeSheet";
	}

	@PostMapping("/checkIn")
	public String checkIn(@ModelAttribute Long employeeId, Model model, @RequestParam String action) {

		if ("checkIn".equals(action)) {
			employeeCheckInOutService.checkIn(employeeId);
//    		model.addAttribute("lastCheckInRecord",LocalTime.now());
		} else if ("checkOut".equals(action)) {
			employeeCheckInOutService.checkOut(employeeId);
//         	model.addAttribute("lastCheckOutRecord",LocalTime.now());
		}

		Employee employee = crudService.findById(employeeId);
		List<EmployeeInOutTable> checkInOutRecords = employeeCheckInOutService.getCheckInOutRecords(employeeId);
		model.addAttribute("emp", employee);
		model.addAttribute("checkInOutRecords", checkInOutRecords);

		return "TimeSheet";
	}

	@GetMapping("/eachEmployeeDuration")
	public String getEachEmployeeDuration(Model model) {
		List<EmployeeInOutTable> all = employeeCheckInOutService.getAll();
		List<EmployeeInOutTable> finalEmployee = new ArrayList<>();

		for (EmployeeInOutTable a : all) {
			LocalDateTime checkInTime = a.getCheckInTime();
			LocalDateTime checkOutTime = a.getCheckOutTime();

			if (checkInTime != null && checkOutTime != null) {
				Duration duration = Duration.between(checkInTime, checkOutTime);
				finalEmployee
						.add(new EmployeeInOutTable(a.getEmployeeId(), a.getCheckInTime(), a.getCheckOutTime(), duration));
			}
		}

	
		model.addAttribute("employeeStartAndEnd", finalEmployee);

		return "ListTimeSheet";

	}
	
	@GetMapping("/getGraphForTimeSheet")
	public String getGraphForTime(Model model) {
		List<EmployeeInOutTable> all = employeeCheckInOutService.getAll();
		List<EmployeeInOutTable> finalEmployee = new ArrayList<>();

		for (EmployeeInOutTable a : all) {
			LocalDateTime checkInTime = a.getCheckInTime();
			LocalDateTime checkOutTime = a.getCheckOutTime();

			if (checkInTime != null && checkOutTime != null) {
				Duration duration = Duration.between(checkInTime, checkOutTime);
				finalEmployee
						.add(new EmployeeInOutTable(a.getEmployeeId(), a.getCheckInTime(), a.getCheckOutTime(), duration));
			}
		}

	
		model.addAttribute("employeeStartAndEnd", finalEmployee);

		return "GraphTimeSheet";
		
	}
}
