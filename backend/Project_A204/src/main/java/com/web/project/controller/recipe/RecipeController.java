package com.web.project.controller.recipe;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.model.recipe.RecipeLikeRequest;
import com.web.project.service.recipe.RecipeLikeService;
import com.web.project.service.recipe.RecipeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Recipe", description = "레시피 API")
@CrossOrigin
@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private RecipeLikeService recipeLikeService;
	
	@GetMapping("/read")
	@ApiOperation(value = "Json File 읽기")
	public void readRecipeJson() {
		recipeService.read();
	}
	
	@GetMapping("/show/main/{recipeId}")
	@ApiOperation(value = "recipeId로 레시피 하나 불러오기")
	public ResponseEntity<Map<String, Object>> showRecipeByRecipeId(@PathVariable("recipeId") int recipeId){
		return recipeService.showRecipeByRecipeId(recipeId);
	}
	
	@GetMapping("/show/sub/{recipeSubId}")
	@ApiOperation(value = "recipeSubId로 레시피 하나 불러오기")
	public ResponseEntity<Map<String, Object>> showRecipeByRecipeSubId(@PathVariable("recipeSubId") int recipeSubId){
		return recipeService.showRecipeByRecipeSubId(recipeSubId);
	}
	
	@GetMapping("/show/new/list")
	@ApiOperation(value = "최신 레시피 10개 불러오기")
	public ResponseEntity<List<Map<String, Object>>> newRecipeList(){
		return recipeService.newRecipeList();
	}
	
	@GetMapping("/show/hot/list")
	@ApiOperation(value = "인기 레시피 10개 불러오기")
	public ResponseEntity<List<Map<String, Object>>> hotRecipeList(){
		return recipeService.hotRecipeList();
	}
	
	@PostMapping("/like")
	@ApiOperation(value = "좋아요", notes="좋아요No => recipe 좋아요+1/좋아요 데이터 추가, 좋아요Yes => recipe 좋아요-1/좋아요 데이터 삭제")
	public Object like(@Valid @RequestBody RecipeLikeRequest recipeLikeRequest) {
		return recipeLikeService.like(recipeLikeRequest);
	}
	
	@GetMapping("/ingredients/{recipeId}")
	@ApiOperation(value = "레시피에 저장된 재료 전달", notes="재료의 이름들만 가져오도록 만들었음")
	public ResponseEntity<List<String>> recipeIngredients(@PathVariable("recipeId") int recipeId){
		return recipeService.recipeIngredients(recipeId);
	}
	
}
