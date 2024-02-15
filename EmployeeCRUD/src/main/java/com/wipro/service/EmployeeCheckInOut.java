package com.wipro.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.EmployeeInOutTable;
import com.wipro.service.repo.EmployeeCheckInOutRepository;

@Service
public class EmployeeCheckInOut {

	@Autowired
	private EmployeeCheckInOutRepository checkInOutRepository;

	public void checkIn(Long employeeId) {
		EmployeeInOutTable emp = checkInOutRepository.findByEmployeeId(employeeId);

		if (emp == null) {
			EmployeeInOutTable checkInRecord = new EmployeeInOutTable(employeeId);
			checkInRecord.setCheckInTime(LocalDateTime.now());
			checkInOutRepository.save(checkInRecord);

		} else {
			emp.setCheckInTime(LocalDateTime.now());
			checkInOutRepository.save(emp);
		}

	}

	public void checkOut(Long employeeId) {
		EmployeeInOutTable emp = checkInOutRepository.findByEmployeeId(employeeId);
		emp.setCheckOutTime(LocalDateTime.now());
		
			LocalDateTime checkInTime = emp.getCheckInTime();
			LocalDateTime checkOutTime = emp.getCheckOutTime();

			if (checkInTime != null && checkOutTime != null) {
				Duration duration = Duration.between(checkInTime, checkOutTime);
				emp.setDuration(duration);
				System.out.println(" is the duration "+duration.toMinutes());
			}
				
		
		checkInOutRepository.save(emp);
	}

	public List<EmployeeInOutTable> getCheckInOutRecords(Long employeeId) {
		return checkInOutRepository.findByEmployeeIdOrderByCheckInTimeAsc(employeeId);
	}
	
	public List<EmployeeInOutTable> getAll(){
		return checkInOutRepository.findAll();
	}
}
