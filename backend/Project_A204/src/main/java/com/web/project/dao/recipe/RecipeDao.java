package com.web.project.dao.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.recipe.Recipe;

@Repository
public interface RecipeDao extends JpaRepository<Recipe, String>{
	
	Recipe findRecipeByRecipeId(int recipeId);
	
	List<Recipe> findTop10ByOrderByRecipeIdDesc();
	
	List<Recipe> findTop10ByOrderByRecipeCountDesc();
	
	Recipe findRecipeByRecipeTitle(String recipeTitle);
	
	Recipe findRecipeByRecipeSubId(int recipeSubId);
}
