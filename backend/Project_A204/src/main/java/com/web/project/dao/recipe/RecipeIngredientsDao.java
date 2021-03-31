package com.web.project.dao.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.recipe.RecipeIngredients;

@Repository
public interface RecipeIngredientsDao extends JpaRepository<RecipeIngredients, String>{

	List<RecipeIngredients> findAllByRecipeId(int recipeId);
	
}
