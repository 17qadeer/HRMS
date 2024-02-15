package com.wipro.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.Pdf;

public interface ResumeRepository extends JpaRepository<Pdf, Integer> {

}
