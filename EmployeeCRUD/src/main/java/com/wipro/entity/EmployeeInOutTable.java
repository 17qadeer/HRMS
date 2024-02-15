package com.wipro.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeInOutTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long employeeId;

	@Column(name = "check_in_time")
	private LocalDateTime checkInTime;

	@Column(name = "check_out_time")
	private LocalDateTime checkOutTime;
	
	private Duration duration;

	public EmployeeInOutTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeInOutTable(Long employeeId, LocalDateTime checkInTime, LocalDateTime checkOutTime, Duration duration) {
		super();
		this.employeeId = employeeId;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.duration = duration;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	

	public EmployeeInOutTable(Long id, Long employeeId, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	@Override
	public String toString() {
		return "EmployeeInOut [id=" + id + ", employeeId=" + employeeId + ", checkInTime=" + checkInTime
				+ ", checkOutTime=" + checkOutTime + "]";
	}

	public EmployeeInOutTable(Long employeeId) {
		super();
		this.employeeId = employeeId;

	}

}
