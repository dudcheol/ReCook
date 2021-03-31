package com.web.project.service.survey;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.recipe.RecipeDao;
import com.web.project.dao.survey.AllergyDao;
import com.web.project.dao.survey.AllergyFoodDao;
import com.web.project.dao.survey.AllergyGroupDao;
import com.web.project.dao.survey.FoodDao;
import com.web.project.dao.survey.FoodLikeDao;
import com.web.project.model.recipe.Recipe;
import com.web.project.model.survey.Allergy;
import com.web.project.model.survey.AllergyGroup;
import com.web.project.model.survey.Food;
import com.web.project.model.survey.FoodLike;
import com.web.project.model.survey.SurveyRequest;

@Service
public class SurveyServiceImpl implements SurveyService{
	
	@Autowired
	private AllergyDao allergyDao;
	
	@Autowired
	private AllergyFoodDao allergyFoodDao;
	
	@Autowired
	private AllergyGroupDao allergyGroupDao;
	
	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private FoodLikeDao foodLikeDao;
	
	@Autowired
	private RecipeDao recipeDao;

	public static final Logger logger = LoggerFactory.getLogger(SurveyServiceImpl.class);

	@Override
	public Object saveSurvey(SurveyRequest surveyRequest, String userId) {
		
		String result = "";
		HttpStatus status = null;
		
		List<String> foodList = surveyRequest.getFoodList();
		List<String> allergyList = surveyRequest.getAllergyList();
		
		try {
			// 좋아하는 음식 리스트 저장
			// 1. 선택된 레시피의 ID를 가져온다.
			for (int i = 0; i < foodList.size(); i++) {
				int recipeId = recipeDao.findRecipeByRecipeTitle(foodList.get(i)).getRecipeId();
				
				// 2. 레시피 ID로 음식 Id를 찾는다.
				FoodLike foodLike = new FoodLike();
				foodLike.setFoodId(foodDao.findFoodByRecipeId(recipeId).getFoodId());
				foodLike.setUserId(userId);
				
				// 3. 저장한다.
				foodLikeDao.save(foodLike);
			}
			
			// 알레르기 목록 저장
			for (int i = 0; i < allergyList.size(); i++) {
				int allergyId = allergyDao.findAllergyByAllergyName(allergyList.get(i)).getAllergyId();
				
				AllergyGroup allergyGroup = new AllergyGroup();
				allergyGroup.setUserId(userId);
				allergyGroup.setAllergyId(allergyId);
				
				allergyGroupDao.save(allergyGroup);
			}
			
			result = "Success";
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("취향, 알레르기 등록 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
		return new ResponseEntity<>(result, status);
	}

	@Override
	public ResponseEntity<List<Recipe>> foodList() {
		List<Recipe> resultList = new ArrayList<Recipe>();
		HttpStatus status = null;
		
		List<Food> foodList = foodDao.findAll();
		
		try {
			if(foodList != null) { // 전달할 레시피 정보가 존재할 때
				for (int i = 0; i < foodList.size(); i++) {
					resultList.add(recipeDao.findRecipeByRecipeId(foodList.get(i).getRecipeId()));
				}
				
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			logger.error("취향 정보 전달 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
		return new ResponseEntity<List<Recipe>>(resultList, status);
	}

	@Override
	public ResponseEntity<List<String>> alleryList() {
		List<String> resultList = new ArrayList<String>();
		HttpStatus status = null;
		
		List<Allergy> allergyList = allergyDao.findAll();
		
		try {
			if(allergyList != null) { // 전달할 레시피 정보가 존재할 때
				for (int i = 0; i < allergyList.size(); i++) {
					resultList.add(allergyList.get(i).getAllergyName());
				}
				
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			logger.error("취향 정보 전달 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
		return new ResponseEntity<List<String>>(resultList, status);
	}
	
}
