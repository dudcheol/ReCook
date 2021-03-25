package com.web.project.model.recipe;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@IdClass(RecipeHashtagPK.class)
@NoArgsConstructor
@AllArgsConstructor
public class RecipeHashtag {
	
	@Id
	private int recipeId;

	@Id
	private int hashtagId;
}
