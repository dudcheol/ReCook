package com.web.project.service.recipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.recipe.RecipeLikeDao;
import com.web.project.dao.recipe.RecipeDao;
import com.web.project.model.recipe.RecipeLike;
import com.web.project.model.recipe.Recipe;
import com.web.project.model.recipe.RecipeLikeRequest;

@Service
public class RecipeLikeServiceImpl implements RecipeLikeService{
	
	@Autowired
	private RecipeLikeDao recipeLikeDao;
	
	@Autowired
	private RecipeDao recipeDao;

	public static final Logger logger = LoggerFactory.getLogger(RecipeLikeServiceImpl.class);

	@Override
	public Object like(RecipeLikeRequest recipeLikeRequest) {
		
		HttpStatus status = null;
		String result = "";
		
		RecipeLike recipeLike = recipeLikeDao.findRecipeLikeByUserIdAndRecipeId(recipeLikeRequest.getUserId(), recipeLikeRequest.getRecipeId());
		Recipe recipe = recipeDao.findRecipeByRecipeId(recipeLikeRequest.getRecipeId());
		
		try {
			if(recipeLike != null) { // 이미 좋아요가 눌려있는 경우
				// 레시피에서 좋아요 개수 -1
				recipe.setRecipeCount(recipe.getRecipeCount()-1);
				// 등록되어 있는 레시피 좋아요 정보 삭제
				recipeLikeDao.delete(recipeLike);
				result = "Like Cancel";
			}else { // 좋아요를 새롭게 누른 경우
				// 레시피 좋아요 개수 +1
				recipe.setRecipeCount(recipe.getRecipeCount()+1);
				// 레시피 좋아요 정보 추가
				RecipeLike newLike = new RecipeLike();
				newLike.setRecipeId(recipeLikeRequest.getRecipeId());
				newLike.setUserId(recipeLikeRequest.getUserId());
				recipeLikeDao.save(newLike);
				result = "Like";
			}
			
			recipeDao.save(recipe);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("좋아요 등록 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
		return new ResponseEntity<>(result, status);
	}

	@Override
	public Object checkLike(RecipeLikeRequest recipeLikeReqeust) {
		HttpStatus status = null;
		String result = "";
		
		RecipeLike recipeLike = recipeLikeDao.findRecipeLikeByUserIdAndRecipeId(recipeLikeReqeust.getUserId(), recipeLikeReqeust.getRecipeId());
		
		try {
			if(recipeLike != null) { // 이미 좋아요가 눌려있는 경우
				result = "Yes";
			}else { // 좋아요를 새롭게 누른 경우
				result = "No";
			}
			
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("좋아요 확인 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	
		return new ResponseEntity<>(result, status);
	}

	
}
