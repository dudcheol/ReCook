package com.web.project.controller.survey;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.model.survey.SurveyRequest;
import com.web.project.service.survey.SurveyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Survey", description = "취향, 알레르기 조사 API")
@CrossOrigin
@RestController
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	private SurveyService surveyService;
	
	@PostMapping("/save")
	@ApiOperation(value = "취향, 알레르기 저장", notes="취향과 알레르기를 선택했을 때 취향의 경우 RecipeId, 알레르기는 AllergyId를 int형 List로 받아와 각각 저장한다.")
	public Object saveSurvey(@Valid @RequestBody SurveyRequest surveyRequest) {
		return null;
	}
	
}
