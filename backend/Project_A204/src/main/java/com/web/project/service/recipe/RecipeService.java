package com.web.project.service.recipe;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface RecipeService {
	
	public void read();
	
	public ResponseEntity<Map<String, Object>> showRecipe(int recipeId);
	
	public ResponseEntity<List<Map<String, Object>>> newRecipeList();
	
	public ResponseEntity<List<Map<String, Object>>> hotRecipeList();
}
