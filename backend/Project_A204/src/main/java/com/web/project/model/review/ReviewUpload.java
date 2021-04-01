package com.web.project.model.review;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class ReviewUpload {
	
	private String userId;
	
	private int recipeId;
	
	private String reviewContext;
	
	private MultipartFile reviewImage;
	
	private double reviewRating;

}
