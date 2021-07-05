package com.web.project.dao.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.recipe.RecipeHashtag;

@Repository
public interface RecipeHashtagDao extends JpaRepository<RecipeHashtag, String> {

	List<RecipeHashtag> findAllByRecipeId(int recipeId);
	
}
