package com.web.project.dao.review;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web.project.model.review.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	public List<Review> findByUserId(String userId);
	
	public List<Review> findByRecipeId(Long recipeId);

}
