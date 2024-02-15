package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.SurveyEmployee;
import com.wipro.service.repo.SurveyRepo;

@Service
public class SurveyService {

	@Autowired
	private SurveyRepo surveyRepo;

	public void saveSurvey(SurveyEmployee survey) {
		
		surveyRepo.save(survey);
	}
	
	public List<SurveyEmployee> getAllSurveys(){
		
		return surveyRepo.findAll();
	}

}
