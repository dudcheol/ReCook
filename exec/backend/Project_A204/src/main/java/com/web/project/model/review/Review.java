package com.web.project.model.review;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.web.project.model.user.User;
import lombok.Data;

@Data
@Entity
@Table(name = "review")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	
	private String userId;
	
	private int recipeSubId;
	
	private int recipeId;
	
	private String reviewContext;
	
	private String reviewImage;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime reviewCreated;

	private double reviewRating;
	
	@OneToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User user;
}
