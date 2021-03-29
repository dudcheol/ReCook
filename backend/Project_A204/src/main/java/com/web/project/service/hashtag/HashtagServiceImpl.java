package com.web.project.service.hashtag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.hashtag.HashtagDao;
import com.web.project.dao.recipe.RecipeDao;
import com.web.project.dao.recipe.RecipeHashtagDao;
import com.web.project.model.hashtag.Hashtag;
import com.web.project.model.recipe.Recipe;
import com.web.project.model.recipe.RecipeHashtag;

@Service
public class HashtagServiceImpl implements HashtagService{
	
	@Autowired
	private HashtagDao hashtagDao;
	
	@Autowired
	private RecipeHashtagDao recipeHashtagDao;
	
	public static final Logger logger = LoggerFactory.getLogger(HashtagServiceImpl.class);
	
	@Override
	public ResponseEntity<List<String>> hashtagListOfRecipe(int recipeId) {
		List<String> resultList = new ArrayList<String>();
		HttpStatus status = HttpStatus.OK;

		List<RecipeHashtag> hashtagList = recipeHashtagDao.findAllByRecipeId(recipeId);

		try {
			for (int i = 0; i < hashtagList.size(); i++) {
				resultList.add(hashtagDao.findHashtagByHashtagId(hashtagList.get(i).getHashtagId()).getHashtagName());
			}
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			logger.error("해시태그 정보 조회 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<String>>(resultList, status);
	}

	@Override
	public ResponseEntity<List<String>> bestHashtagList() {
		List<String> resultList = new ArrayList<String>();
		HttpStatus status = HttpStatus.OK;
		
		try {
			List<Hashtag> hashtagList = hashtagDao.findTop10ByOrderByHashtagCountDesc();
			
			for (int i = 0; i < hashtagList.size(); i++) {
				resultList.add(hashtagList.get(i).getHashtagName());
			}
			
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			logger.error("해시태그 상위 10개 전달 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<String>>(resultList, status);
	}

}
