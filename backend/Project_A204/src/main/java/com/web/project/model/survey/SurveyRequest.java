package com.web.project.model.survey;

import java.util.List;

import lombok.Data;

@Data
public class SurveyRequest {

	private List<Integer> foodList;
	
	private List<Integer> allergyList; 
	
}
