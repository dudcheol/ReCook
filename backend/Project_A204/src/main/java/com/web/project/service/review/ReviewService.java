package com.web.project.service.review;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.web.project.model.review.Review;
import com.web.project.model.review.ReviewUpload;

public interface ReviewService {
	
	public ResponseEntity<Review> createReview(ReviewUpload reviewUpload);

	public ResponseEntity<Page<Review>> findAll(Pageable pageable);
	
	public ResponseEntity<Map<String, Object>> findByReview(int reviewId);
	
	public ResponseEntity<List<Review>> findByUser(String userName);
	
	public ResponseEntity<List<Review>> findByRecipe(int recipeSubId);
	
}
