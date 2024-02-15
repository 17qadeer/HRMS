package com.wipro.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.EmployeeInOutTable;

public interface EmployeeCheckInOutRepository  extends JpaRepository<EmployeeInOutTable, Long>{
	List<EmployeeInOutTable> findByEmployeeIdOrderByCheckInTimeAsc(Long employeeId);

    EmployeeInOutTable findTopByEmployeeIdOrderByCheckInTimeDesc(Long employeeId);

    EmployeeInOutTable findByEmployeeId(Long employeeId);
}
