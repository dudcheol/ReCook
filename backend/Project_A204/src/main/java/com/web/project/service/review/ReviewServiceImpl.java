package com.web.project.service.review;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.review.ReviewDao;
import com.web.project.exception.NoDataFoundException;
import com.web.project.model.review.Review;
import com.web.project.model.review.ReviewUpload;
import com.web.project.service.recipe.RecipeServiceImpl;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao reviewDao;
	
	public static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	@Override
	public ResponseEntity<Review> createReview(ReviewUpload reviewUpload) {
		HttpStatus status = null;
		
		String filePath = "/home/ubuntu/img";
		String fileName = "review" + (reviewDao.count() + 1) + "." + reviewUpload.getReviewImage().getContentType().split("/")[1];

		File saveFile = new File(filePath, fileName);
		
		Review review = new Review();

		try {
			reviewUpload.getReviewImage().transferTo(saveFile);
			fileName = "http://j4a204.p.ssafy.io/img/" + fileName;
			
			review.setRecipeId(reviewUpload.getRecipeId());
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
	public ResponseEntity<List<Review>> findAll() {
		HttpStatus status = null;
		List<Review> reviewList = reviewDao.findAllByOrderByReviewIdDesc();
		
		try {
			if(reviewList != null) {
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("리뷰 전체 가져오기 실패 : {}", e);
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<List<Review>>(reviewList, status);
	}
	
	@Override
	public ResponseEntity<Review> findByReview(int reviewId) {
		HttpStatus status = null;
		Review review = reviewDao.findReviewByReviewId(reviewId);
		
		try {
			if(review != null) {
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("해당 리뷰 가져오기 실패 : {}", e);
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Review>(review, status);
	}

	@Override
	public ResponseEntity<List<Review>> findByUser(String userId) {
		HttpStatus status = null;
		List<Review> reviewList = reviewDao.findAllByUserId(userId);
		
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
