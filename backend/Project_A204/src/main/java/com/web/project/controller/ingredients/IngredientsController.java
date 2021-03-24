package com.web.project.controller.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.dao.ingredients.IngredientsBigDao;
import com.web.project.model.ingredients.IngredientsBig;
import com.web.project.model.ingredients.IngredientsMiddle;
import com.web.project.service.ingredients.IngredientsService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

	@Autowired
	private IngredientsService ingredientsService;
	
	@GetMapping("/getBigList")
	@ApiOperation("대분류 전체 리스트 가져오기")
	public ResponseEntity<List<IngredientsBig>> getIngredientsBigList(){
		
		return ingredientsService.getIngredientsBigList();
	}
	
	@GetMapping("/getMiddleList/{bigId}")
	@ApiOperation("특정 대분류에 해당하는 중분류 리스트 가져오기")
	public ResponseEntity<List<IngredientsMiddle>> getIngredientsMiddleList(@PathVariable("bigId")int bigId){
		
		return ingredientsService.getIngredientsMiddleList(bigId);
	}
	
	
	
	
}
