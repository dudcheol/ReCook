package com.web.project.model.recipe;

import lombok.Data;

@Data
public class RecipeLike {
	
	private boolean isLike;
	
	private int recipeId;
	
	private String userId;
	
}
