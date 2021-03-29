package com.web.project.dao.review;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.project.model.review.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, String> {

	public Review findReviewByReviewId(int reviewId);
	
	public List<Review> findAllByUserId(String userId);
	
	public List<Review> findAllByRecipeId(int recipeId);
	
	public List<Review> findAllByOrderByReviewIdDesc();
}
