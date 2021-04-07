package com.web.project.dao.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.project.model.recipe.RecipeLike;

@Repository
public interface RecipeLikeDao extends JpaRepository<RecipeLike, String>{

	RecipeLike findRecipeLikeByUserIdAndRecipeId(String userId, int recipeId);
	
	@Query(value = "SELECT * FROM recipe_like WHERE user_id = :userId ORDER BY like_id DESC", nativeQuery = true)
	Page<RecipeLike> findAllByUserId(@Param("userId") String userId, Pageable pageable);
	
}
