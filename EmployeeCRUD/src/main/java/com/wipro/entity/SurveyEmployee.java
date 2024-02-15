package com.wipro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SurveyEmployee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long employeeId;
	private int workEnvironmentRating;
	private int jobSatisfactionLevel;
	private int leadershipRating;

	private String feedback;
	private String improvementSuggestions;
	private boolean recommendToOthers;

	public SurveyEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SurveyEmployee( Long employeeId, int workEnvironmentRating, int jobSatisfactionLevel, int leadershipRating,
			String feedback, String improvementSuggestions, boolean recommendToOthers) {
		super();
		
		this.employeeId = employeeId;
		this.workEnvironmentRating = workEnvironmentRating;
		this.jobSatisfactionLevel = jobSatisfactionLevel;
		this.leadershipRating = leadershipRating;

		this.feedback = feedback;
		this.improvementSuggestions = improvementSuggestions;
		this.recommendToOthers = recommendToOthers;
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

	public int getWorkEnvironmentRating() {
		return workEnvironmentRating;
	}

	public void setWorkEnvironmentRating(int workEnvironmentRating) {
		this.workEnvironmentRating = workEnvironmentRating;
	}

	public int getJobSatisfactionLevel() {
		return jobSatisfactionLevel;
	}

	public void setJobSatisfactionLevel(int jobSatisfactionLevel) {
		this.jobSatisfactionLevel = jobSatisfactionLevel;
	}

	public int getLeadershipRating() {
		return leadershipRating;
	}

	public void setLeadershipRating(int leadershipRating) {
		this.leadershipRating = leadershipRating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getImprovementSuggestions() {
		return improvementSuggestions;
	}

	public void setImprovementSuggestions(String improvementSuggestions) {
		this.improvementSuggestions = improvementSuggestions;
	}

	public boolean isRecommendToOthers() {
		return recommendToOthers;
	}

	public void setRecommendToOthers(boolean recommendToOthers) {
		this.recommendToOthers = recommendToOthers;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", employeeId=" + employeeId + ", workEnvironmentRating=" + workEnvironmentRating
				+ ", jobSatisfactionLevel=" + jobSatisfactionLevel + ", leadershipRating=" + leadershipRating
				+  "feedback=" + feedback
				+ ", improvementSuggestions=" + improvementSuggestions + ", recommendToOthers=" + recommendToOthers
				+ "]";
	}

}
