package com.web.project.controller.survey;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.model.recipe.Recipe;
import com.web.project.model.survey.SurveyRequest;
import com.web.project.model.user.User;
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
	
	@PostMapping("/save/{userId}")
	@ApiOperation(value = "취향, 알레르기 저장", notes = "취향과 알레르기를 선택했을 때 각각 String을 List로 받아와 각각 저장한다.")
	public Object saveSurvey(@Valid @RequestBody SurveyRequest surveyRequest, @PathVariable("userId") String userId) {
		return surveyService.saveSurvey(surveyRequest, userId);
	}
	
	@GetMapping("/food")
	@ApiOperation(value = "취향 조사 리스트 전달", notes = "좋아하는 음식을 선택할 수 있도록 미리 선별해둔 레시피 20개 전달")
	public ResponseEntity<List<Recipe>> foodList(){
		return surveyService.foodList();
	}
	
	@GetMapping("/allergy")
	@ApiOperation(value = "알레르기 리스트 전달", notes = "모든 알레르기 리스트를 전달")
	public ResponseEntity<List<String>> alleryList(){
		return surveyService.alleryList();
	}
}
