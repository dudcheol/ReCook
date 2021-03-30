package com.web.project.model.recipe;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recipeId;
	
	private String recipeTitle;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime recipeCreated;
	
	private String recipeMainImage;
	
	private String recipeImage;
	
	private String recipeContext;
	
	private String recipeIngredient;
	
	private String recipeTime;
	
	private int recipeCount;
}
