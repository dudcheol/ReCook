package com.web.project.model.ingredients;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class IngredientsRequest {
	
	private String userId;
	
	private List<String> ingredientList;
}
