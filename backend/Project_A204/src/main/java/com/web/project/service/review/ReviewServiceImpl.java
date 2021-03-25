package com.web.project.service.review;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.project.dao.review.ReviewRepository;
import com.web.project.exception.NoDataFoundException;
import com.web.project.model.review.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Review createReview(Review review) {
		return reviewRepository.save(review);
	}
	
	@Override
	public List<Review> findAll() {
		List<Review> reviewList = reviewRepository.findAll();
		
		if(reviewList.size() > 0) {
			return reviewList;
		}
		return null;
	}
	
	@Override
	public Review findByReview(long reviewId) {
		return reviewRepository.findById(reviewId).orElseThrow(() -> new NoDataFoundException("post"));
	}

	@Override
	public List<Review> findByUser(String userId) {
		List<Review> reviewList = reviewRepository.findByUserId(userId);
		
		if(reviewList.size() > 0) {
			return reviewList;
		}
		return null;
	}

	@Override
	public List<Review> findByRecipe(Long recipeId) {
		List<Review> reviewList = reviewRepository.findByRecipeId(recipeId);
		
		if(reviewList.size() > 0) {
			return reviewList;
		}
		return null;
	}

	@Override
	public long countAll() {
		return reviewRepository.count();
	}

}
