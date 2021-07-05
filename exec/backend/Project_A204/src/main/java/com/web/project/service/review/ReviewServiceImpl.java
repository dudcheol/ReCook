package com.web.project.service.review;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.recipe.RecipeDao;
import com.web.project.dao.review.ReviewDao;
import com.web.project.dao.user.UserDao;
import com.web.project.model.recipe.Recipe;
import com.web.project.model.review.Review;
import com.web.project.model.review.ReviewUpload;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private RecipeDao recipeDao;
	
	@Autowired
	private UserDao userDao;
	
	public static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	@Override
	public ResponseEntity<Review> createReview(ReviewUpload reviewUpload) {
		HttpStatus status = null;
		
		String filePath = "/home/ubuntu/img/review";
		String fileName = "review" + (reviewDao.findMaxReviewId() + 1) + "." + reviewUpload.getReviewImage().getContentType().split("/")[1];

		File saveFile = new File(filePath, fileName);
		
		Review review = new Review();

		try {
			reviewUpload.getReviewImage().transferTo(saveFile);
			fileName = "http://j4a204.p.ssafy.io/img/review/" + fileName;
			
			review.setRecipeId(reviewUpload.getRecipeId());
			review.setRecipeSubId(recipeDao.findRecipeByRecipeId(reviewUpload.getRecipeId()).getRecipeSubId());
			review.setUserId(reviewUpload.getUserId());
			review.setReviewContext(reviewUpload.getReviewContext());
			review.setReviewRating(reviewUpload.getReviewRating());
			review.setReviewImage(fileName);
			reviewDao.save(review);
			
			status = HttpStatus.OK;
		}
		catch (IOException e) {
			logger.error("리뷰 등록 실패 : {}", e);
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Review>(review, status);
	}
	
	@Override
	public ResponseEntity<List<Map<String, Object>>> findAll(Pageable pageable) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		
		HttpStatus status = null;
		Page<Review> reviewList = reviewDao.findAllByOrderByReviewIdDesc(pageable);
		
		try {
			if(reviewList != null) {
				Iterator<Review> iterator = reviewList.iterator();
				while (iterator.hasNext()) {
					Review review = iterator.next();
					
					Map<String, Object> resultMap = new HashMap<String, Object>();
					
					resultMap.put("review", review);
					resultMap.put("recipeTitle", recipeDao.findRecipeByRecipeId(review.getRecipeId()).getRecipeTitle());
					
					resultList.add(resultMap);
				}
				
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("리뷰 전체 가져오기 실패 : {}", e);
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<Map<String, Object>>>(resultList, status);
	}
	
	@Override
	public ResponseEntity<List<Map<String, Object>>> findAllImage(Pageable pageable) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		
		HttpStatus status = null;
		Page<Review> reviewList = reviewDao.findAllImageByOrderByReviewIdDesc(pageable);
		
		try {
			if(reviewList != null) {
				Iterator<Review> iterator = reviewList.iterator();
				while (iterator.hasNext()) {
					Review review = iterator.next();
					
					Map<String, Object> resultMap = new HashMap<String, Object>();
					
					resultMap.put("review", review);
					resultMap.put("recipeTitle", recipeDao.findRecipeByRecipeId(review.getRecipeId()).getRecipeTitle());
					
					resultList.add(resultMap);
				}
				
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("리뷰 중 사진 있는거 가져오기 실패 : {}", e);
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<Map<String, Object>>> (resultList, status);
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> findByReview(int reviewId) {
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		
		Review review = reviewDao.findReviewByReviewId(reviewId);
		
		try {
			if(review != null) {
				resultMap.put("review", review);
				
				Recipe recipe = recipeDao.findRecipeByRecipeSubId(review.getRecipeSubId());
				resultMap.put("recipeTitle", recipe.getRecipeTitle());
				resultMap.put("recipeRating", recipe.getRecipeCount());
				resultMap.put("recipeImage", recipe.getRecipeMainImage());
				
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("해당 리뷰 가져오기 실패 : {}", e);
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Override
	public ResponseEntity<List<Review>> findByUser(String userName) {
		HttpStatus status = null;
		List<Review> reviewList = reviewDao.findAllByUserId(userDao.findUserByUserName(userName).getUserId());
		
		try {
			if(reviewList != null) {
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("유저 ID 기반 리뷰 전체 가져오기 실패 : {}", e);
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<Review>>(reviewList, status);
	}

	@Override
	public ResponseEntity<List<Review>> findByRecipe(int recipeId) {
		HttpStatus status = null;
		List<Review> reviewList = reviewDao.findAllByRecipeId(recipeId);
		
		try {
			if(reviewList != null) {
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("Recipe Id 기반 리뷰 전체 가져오기 실패 : {}", e);
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<Review>>(reviewList, status);
	}

	

}
