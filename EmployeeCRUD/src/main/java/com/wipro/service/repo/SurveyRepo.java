package com.wipro.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.SurveyEmployee;

@Repository
public interface SurveyRepo extends JpaRepository<SurveyEmployee, Long> {

}
