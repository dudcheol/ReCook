package com.web.project.dao.review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.web.project.model.review.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, String> {

	public Review findReviewByReviewId(int reviewId);
	
	public List<Review> findAllByUserId(String userId);

//	public List<Review> findAllByRecipeId(int recipeId);
	
	public Page<Review> findAllByOrderByReviewIdDesc(Pageable pageable);
	
	@Query(value = "SELECT * FROM review WHERE review_image is not null ORDER BY review_id DESC", nativeQuery = true)
	public Page<Review> findAllImageByOrderByReviewIdDesc(Pageable pageable);
	
	public List<Review> findAllByRecipeSubId(int recipeSubId);
}
