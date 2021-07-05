package com.web.project.service.recipe;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.web.project.model.ingredients.IngredientsRequest;
import com.web.project.model.recipe.Recipe;

public interface RecipeService {
	
	public void read();
	
	public ResponseEntity<Map<String, Object>> showRecipeByRecipeId(int recipeId);
	
	public ResponseEntity<Map<String, Object>> showRecipeByRecipeSubId(int recipeSubId);
	
	public ResponseEntity<List<Map<String, Object>>> newRecipeList();
	
	public ResponseEntity<Page<Recipe>> newRecipeListAll(Pageable pageable);
	
	public ResponseEntity<List<Map<String, Object>>> hotRecipeList();
	
	public ResponseEntity<Page<Recipe>> hotRecipeListAll(Pageable pageable);
	
	public ResponseEntity<List<String>> recipeIngredients(int recipeId);
	
	public ResponseEntity<List<Recipe>> selectIngredinets(IngredientsRequest ingredientsRequest, Pageable pageable);
	
	public ResponseEntity<List<Recipe>> selectIngredinetsWithAllergy(IngredientsRequest ingredientsRequest, Pageable pageable);

	public ResponseEntity<Page<Recipe>> allRecipeByTitle(String title, Pageable pageable);
}
