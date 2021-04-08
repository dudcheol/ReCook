package com.web.project.model.recipe;

import java.io.Serializable;

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
//@IdClass(RecipeIngredientsPK.class)
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredients{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ingredientsId;
	
	private int recipeId;
	
	private int smallId;
}
