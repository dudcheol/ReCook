package com.web.project.service.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.ingredients.IngredientsBigDao;
import com.web.project.dao.ingredients.IngredientsMiddleDao;
import com.web.project.dao.ingredients.IngredientsSmallDao;
import com.web.project.model.ingredients.IngredientsBig;
import com.web.project.model.ingredients.IngredientsMiddle;

@Service
public class IngredientsServiceImpl implements IngredientsService {
	
	@Autowired
	private IngredientsBigDao ingredientsBigDao;
	private IngredientsMiddleDao ingredientsMiddleDao;
	private IngredientsSmallDao ingredientsSmallDao;
	
	@Override
	public ResponseEntity<List<IngredientsBig>> getIngredientsBigList(){
		return new ResponseEntity<List<IngredientsBig>>(ingredientsBigDao.findAll(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<IngredientsMiddle>> getIngredientsMiddleList(int bigId){
		return new ResponseEntity<List<IngredientsMiddle>>(ingredientsMiddleDao.findAll(), HttpStatus.OK);
	}
	
}
