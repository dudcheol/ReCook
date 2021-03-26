package com.web.project.service.review;

import java.util.List;
import com.web.project.model.review.Review;

public interface ReviewService {
	
	public Review createReview(Review review);

	public List<Review> findAll();

	public Review findByReview(long reviewId);
	
	public List<Review> findByUser(String userId);
	
	public List<Review> findByRecipe(Long recipeId);
	
	public long countAll();

}
