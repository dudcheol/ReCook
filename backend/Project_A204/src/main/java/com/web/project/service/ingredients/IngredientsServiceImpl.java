package com.web.project.service.ingredients;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.ingredients.IngredientsBigDao;
import com.web.project.dao.ingredients.IngredientsMiddleDao;
import com.web.project.dao.ingredients.IngredientsSmallDao;
import com.web.project.dao.survey.AllergyFoodDao;
import com.web.project.dao.survey.AllergyGroupDao;
import com.web.project.model.ingredients.IngredientsBig;
import com.web.project.model.ingredients.IngredientsMiddle;
import com.web.project.model.ingredients.IngredientsSmall;
import com.web.project.model.survey.Allergy;
import com.web.project.model.survey.AllergyFood;
import com.web.project.model.survey.AllergyGroup;
import com.web.project.service.recipe.RecipeServiceImpl;

@Service
public class IngredientsServiceImpl implements IngredientsService {
	
	@Autowired
	private IngredientsBigDao ingredientsBigDao;
	
	@Autowired
	private IngredientsMiddleDao ingredientsMiddleDao;
	
	@Autowired
	private IngredientsSmallDao ingredientsSmallDao;
	
	@Autowired
	private AllergyGroupDao allergyGroupDao;
	
	@Autowired
	private AllergyFoodDao allergyFoodDao;
	
	public static final Logger logger = LoggerFactory.getLogger(IngredientsServiceImpl.class);
	
	@Override
	public ResponseEntity<List<IngredientsBig>> getIngredientsBigList(){
		return new ResponseEntity<List<IngredientsBig>>(ingredientsBigDao.findAll(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<IngredientsMiddle>> getIngredientsMiddleList(int bigId){
		return new ResponseEntity<List<IngredientsMiddle>>(ingredientsMiddleDao.findAllIngredientsMiddleByBigId(bigId), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<IngredientsSmall>> getIngredientsSmallList(int midId){
		return new ResponseEntity<List<IngredientsSmall>>(ingredientsSmallDao.findAllIngredientsSmallByMidId(midId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> allergyIngredient(String userId) {
		List<String> resultList = new ArrayList<String>();
		HttpStatus status = null;
		
		List<AllergyGroup> allergyGroupList = allergyGroupDao.findAllByUserId(userId);
		
		try {
			for (int i = 0; i < allergyGroupList.size(); i++) {
				List<AllergyFood> allergyFood = allergyFoodDao.findAllByAllergyId(allergyGroupList.get(i).getAllergyId());
				
				for (int j = 0; j < allergyFood.size(); j++) {
					resultList.add(ingredientsSmallDao.findIngredientsSmallBySmallId(allergyFood.get(j).getSmallId()).getSmallName());
				}
			}
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("알레르기 재료 정보 조회 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<String>> (resultList, status);
	}
		
	
	
}
