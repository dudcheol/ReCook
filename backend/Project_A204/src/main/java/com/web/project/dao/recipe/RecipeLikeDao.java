package com.web.project.dao.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.recipe.RecipeLike;


@Repository
public interface RecipeLikeDao extends JpaRepository<RecipeLike, String>{

	RecipeLike findRecipeLikeByUserIdAndRecipeId(String userId, int recipeId);
	
}
