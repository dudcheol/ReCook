package com.web.project.service.ingredients;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.web.project.model.ingredients.IngredientsBig;
import com.web.project.model.ingredients.IngredientsMiddle;
import com.web.project.model.ingredients.IngredientsSmall;

public interface IngredientsService {
	
	public ResponseEntity<List<IngredientsBig>> getIngredientsBigList();
	public ResponseEntity<List<IngredientsMiddle>> getIngredientsMiddleList(int bigId);
	public ResponseEntity<List<IngredientsSmall>> getIngredientsSmallList(int midId);
	public ResponseEntity<List<String>> allergyIngredient(String userId);
	
}
