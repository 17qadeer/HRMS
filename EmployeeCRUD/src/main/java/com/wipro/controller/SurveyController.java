package com.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wipro.entity.Employee;
import com.wipro.entity.SurveyEmployee;
import com.wipro.service.EmployeeCRUDService;
import com.wipro.service.SurveyService;

@Controller
public class SurveyController {

	@Autowired
	private EmployeeCRUDService employeeCRUDService;

	@Autowired
	private SurveyService surveyService;

	@GetMapping("/takeSurvey/{id}")
	public String getSurvey(@PathVariable Long id, Model model) {

		Employee empolyee = employeeCRUDService.findById(id);

		model.addAttribute("survey", new SurveyEmployee());
		model.addAttribute("emp", empolyee);
		return "SurveyForEmployee";

	}

	@PostMapping("/submitSurvey/{id}")
	public String submitSurvey(@ModelAttribute SurveyEmployee survey, Model model) {

		surveyService.saveSurvey(survey);
		model.addAttribute("message", "Sucessfully completed the survey");
		return "surveySubmit";
	}

	@GetMapping("/surveyGraph")
	public String getSurveysGraph(Model model) {
		List<SurveyEmployee> allSurveys = surveyService.getAllSurveys();

		// Aggregate ratings
		int totalWorkEnvironmentRating = 0;
		int totalJobSatisfactionRating = 0;
		int totalLeadershipRating = 0;
		int numberOfSurveys = allSurveys.size();

		for (SurveyEmployee survey : allSurveys) {
			totalWorkEnvironmentRating += survey.getWorkEnvironmentRating();
			totalJobSatisfactionRating += survey.getJobSatisfactionLevel();
			totalLeadershipRating += survey.getLeadershipRating();
		}

		// Calculate average ratings
		double averageWorkEnvironmentRating = (double) totalWorkEnvironmentRating / numberOfSurveys;
		double averageJobSatisfactionRating = (double) totalJobSatisfactionRating / numberOfSurveys;
		double averageLeadershipRating = (double) totalLeadershipRating / numberOfSurveys;

		// Pass aggregated ratings to the view
		model.addAttribute("averageWorkEnvironmentRating", averageWorkEnvironmentRating);
		model.addAttribute("averageJobSatisfactionRating", averageJobSatisfactionRating);
		model.addAttribute("averageLeadershipRating", averageLeadershipRating);

		model.addAttribute("surveys", allSurveys);
		return "SurveyGraph";
	}

	@GetMapping("/getAllSurveys")
	public String getAllSurveys(Model model) {

		model.addAttribute("surveys", surveyService.getAllSurveys());
		return "SurveyList";
	}

}
