package com.web.project.dao.recipe;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.project.model.recipe.Recipe;

@Repository
public interface RecipeDao extends JpaRepository<Recipe, String>{
	
	Recipe findRecipeByRecipeId(int recipeId);
	
	List<Recipe> findTop10ByOrderByRecipeIdDesc();
	
	List<Recipe> findTop10ByOrderByRecipeCountDesc();
	
	Recipe findRecipeByRecipeTitle(String recipeTitle);
	
	Recipe findRecipeByRecipeSubId(int recipeSubId);
	
	@Query(value = "SELECT * FROM recipe WHERE recipe_ingredient LIKE %:ingredientName% ORDER BY recipe_count DESC", nativeQuery = true)
	Page<Recipe> findRecipeWithIngredientOne(@Param("ingredientName") String ingredientName, Pageable pageable);
	
	@Query(value = "SELECT * FROM recipe WHERE recipe_ingredient LIKE %:ingredientNameOne% AND recipe_ingredient LIKE %:ingredientNameTwo% ORDER BY recipe_count DESC", nativeQuery = true)
	Page<Recipe> findRecipeWithIngredientTwo(@Param("ingredientNameOne") String ingredientNameOne, @Param("ingredientNameTwo") String ingredientNameTwo, Pageable pageable);
	
	@Query(value = "SELECT * FROM recipe WHERE recipe_ingredient LIKE %:ingredientNameOne% AND recipe_ingredient LIKE %:ingredientNameTwo% AND recipe_ingredient LIKE %:ingredientNameThree% ORDER BY recipe_count DESC", nativeQuery = true)
	Page<Recipe> findRecipeWithIngredientThree(@Param("ingredientNameOne") String ingredientNameOne, @Param("ingredientNameTwo") String ingredientNameTwo, @Param("ingredientNameThree") String ingredientNameThree, Pageable pageable);
	
	@Query(value = "SELECT * FROM recipe WHERE recipe_title LIKE %:title% ORDER BY recipe_count DESC", nativeQuery = true)
	Page<Recipe> findAllByTitle(@Param("title") String Title, Pageable pageable);
}
