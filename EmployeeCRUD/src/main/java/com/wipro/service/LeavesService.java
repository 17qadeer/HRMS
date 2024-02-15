package com.wipro.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Employee;
import com.wipro.service.repo.HRMSRepo;

@Service
public class LeavesService {

	@Autowired
	private HRMSRepo hrmsRepo;

	public long calculateNumberOfDays(LocalDate startDate, LocalDate endDate) {

		long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

		if (startDate.isAfter(endDate)) {
			System.out.println(startDate);
			System.out.println(endDate);
			return 0;
		}
		return daysBetween + 1;
	}

	public String applyLeave(Long employeeId, long numberOfDays) {
		Employee employee = hrmsRepo.findById(employeeId).orElse(null);
		if (employee == null) {
			return "Employee not found";
		}

		int currentLeaveBalance = employee.getLeaves();
		int remainingLeaveBalance = (int) (currentLeaveBalance - numberOfDays);

		if (remainingLeaveBalance >= 0) {
			employee.setLeaves(remainingLeaveBalance);
			hrmsRepo.save(employee);
			return "Leave applied successfully for Employee ";
		} else {
			return "Insufficient leave balance for Employee ";
		}
	}
}
