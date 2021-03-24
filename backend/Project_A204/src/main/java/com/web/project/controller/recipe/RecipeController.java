package com.web.project.controller.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.service.recipe.RecipeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Recipe", description = "Recipe API")
@CrossOrigin
@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/read")
	@ApiOperation(value = "Json File 읽기")
	public void readRecipe() {
		recipeService.read();
	}
	
	
}
