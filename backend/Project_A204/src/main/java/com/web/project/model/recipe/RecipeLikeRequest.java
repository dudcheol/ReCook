package com.web.project.model.recipe;

import lombok.Data;

@Data
public class RecipeLikeRequest {
	
	private int recipeId;
	
	private String userId;
	
}
