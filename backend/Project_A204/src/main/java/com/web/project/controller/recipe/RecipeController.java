package com.web.project.controller.recipe;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public void readRecipeJson() {
		recipeService.read();
	}
	
	@GetMapping("/show/{recipeId}")
	@ApiOperation(value = "레시피 하나 불러오기")
	public ResponseEntity<Map<String, Object>> showRecipe(@PathVariable("recipeId") int recipeId){
		return recipeService.showRecipe(recipeId);
	}
	
	@GetMapping("/show/new/list")
	@ApiOperation(value = "최신 레시피 10개 불러오기")
	public ResponseEntity<List<Map<String, Object>>> newRecipeList(){
		return recipeService.newRecipeList();
	}
}
