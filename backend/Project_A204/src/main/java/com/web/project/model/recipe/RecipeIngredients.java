package com.web.project.model.recipe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@IdClass(RecipeIngredientsPK.class)
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredients {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ingredientsId;
	
	@Id
	private int recipeId;
	
	@Id
	private int smallId;
}
