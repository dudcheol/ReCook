package com.web.project.service.ingredients;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.web.project.model.ingredients.IngredientsBig;
import com.web.project.model.ingredients.IngredientsMiddle;

public interface IngredientsService {
	
	public ResponseEntity<List<IngredientsBig>> getIngredientsBigList();
	public ResponseEntity<List<IngredientsMiddle>> getIngredientsMiddleList(int bigId);
	
}
