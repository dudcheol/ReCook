package com.web.project.dao.review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.web.project.model.review.Review;

@Repository
public interface ReviewDao extends JpaRepository<Review, String> {

	public Review findReviewByReviewId(int reviewId);
	
	@Query(value = "SELECT * FROM review WHERE user_id = :userId ORDER BY review_id DESC", nativeQuery = true)
	public List<Review> findAllByUserId(@Param("userId") String userId);

	@Query(value = "SELECT * FROM review WHERE recipe_id = :recipeId AND review_image is not null", nativeQuery = true)
	public List<Review> findAllByRecipeId(@Param("recipeId") int recipeId);
	
	public Page<Review> findAllByOrderByReviewIdDesc(Pageable pageable);
	
	@Query(value = "SELECT * FROM review WHERE review_image is not null ORDER BY review_id DESC", nativeQuery = true)
	public Page<Review> findAllImageByOrderByReviewIdDesc(Pageable pageable);
	
	public List<Review> findAllByRecipeSubId(int recipeSubId);
	
	@Query(value = "select max(review_id) from review", nativeQuery = true)
	public int findMaxReviewId();

}
