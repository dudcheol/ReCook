package com.web.project.service.review;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.web.project.model.review.Review;
import com.web.project.model.review.ReviewUpload;

public interface ReviewService {
	
	public ResponseEntity<Review> createReview(ReviewUpload reviewUpload);

	public ResponseEntity<List<Review>> findAll();
	
	public ResponseEntity<Review> findByReview(int reviewId);
	
	public ResponseEntity<List<Review>> findByUser(String userId);
	
	public ResponseEntity<List<Review>> findByRecipe(int recipeId);
	
}
