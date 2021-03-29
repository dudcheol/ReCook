package com.web.project.service.review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.web.project.model.review.Review;
import com.web.project.model.review.ReviewUpload;

public interface ReviewService {
	
	public ResponseEntity<Review> createReview(ReviewUpload reviewUpload);

	public ResponseEntity<Page<Review>> findAll(Pageable pageable);
	
	public ResponseEntity<Review> findByReview(int reviewId);
	
	public ResponseEntity<List<Review>> findByUser(String userId);
	
	public ResponseEntity<List<Review>> findByRecipe(int recipeId);
	
}
