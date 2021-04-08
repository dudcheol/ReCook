package com.web.project.service.hashtag;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.web.project.model.hashtag.Hashtag;

public interface HashtagService {

	public ResponseEntity<List<String>> hashtagListOfRecipe(int recipeId);
	
	public ResponseEntity<List<String>> bestHashtagList();
}
