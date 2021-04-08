package com.web.project.controller.hashtag;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.model.hashtag.Hashtag;
import com.web.project.service.hashtag.HashtagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Hashtag", description = "해시태그 API")
@CrossOrigin
@RestController
@RequestMapping("/hashtag")
public class HashtagController {
	
	@Autowired
	private HashtagService hashtagService;

	@GetMapping("/hashtagList/{recipeId}")
	@ApiOperation(value="특정 대분류에 해당하는 중분류 리스트 가져오기")
	public ResponseEntity<List<String>> hashtagListOfRecipe(@PathVariable("recipeId")int recipeId){
		return hashtagService.hashtagListOfRecipe(recipeId);
	}
	
	@GetMapping("/show/hastagList/best")
	@ApiOperation(value = "가장 많은 해시태그 10개 전달")
	public ResponseEntity<List<String>> bestHashtagList(){
		return hashtagService.bestHashtagList();
	}
}
