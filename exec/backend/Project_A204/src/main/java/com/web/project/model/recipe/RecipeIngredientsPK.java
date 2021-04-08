package com.web.project.model.recipe;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecipeIngredientsPK implements Serializable{
	
	private int ingredientsId;
	
	private int recipeId;
	
	private int smallId;
	
}
