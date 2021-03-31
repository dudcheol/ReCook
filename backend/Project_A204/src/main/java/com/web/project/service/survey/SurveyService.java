package com.web.project.service.survey;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.web.project.model.recipe.Recipe;
import com.web.project.model.survey.SurveyRequest;

public interface SurveyService {

	public Object saveSurvey(SurveyRequest surveyRequest, String userId);
	
	public ResponseEntity<List<Recipe>> foodList();
	
	public ResponseEntity<List<String>> alleryList();
	
}
