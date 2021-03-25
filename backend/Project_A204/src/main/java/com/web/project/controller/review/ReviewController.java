package com.web.project.controller.review;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.project.model.review.Review;
import com.web.project.model.review.ReviewUpload;
import com.web.project.service.review.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Review", description = "리뷰 API")
@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@ApiOperation(value = "리뷰 작성", notes = "한장의 사진과 내용을 작성합니다.")
	@PostMapping("")
	public ResponseEntity<String> createReview(ReviewUpload reviewUpload) {
		
		System.out.println(reviewUpload.getReviewImage().getContentType());
		
		String filePath = "/var/www/html/dist/img/review";
		String fileName = "reviewId" + (reviewService.countAll() + 1) + "." + reviewUpload.getReviewImage().getContentType().split("/")[1];

		File saveFile = new File(filePath, fileName);

		try {
			reviewUpload.getReviewImage().transferTo(saveFile);
			fileName = "http://j4a204.p.ssafy.io/img/review/" + fileName;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Review review = new Review();
		review.setRecipeId(reviewUpload.getRecipeId());
		review.setUserId(reviewUpload.getUserId());
		review.setReviewContext(reviewUpload.getReviewContext());
		review.setReviewImage(fileName);
		
		reviewService.createReview(review);
		
		return new ResponseEntity<String>("Success", HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "모든 리뷰 조회", notes = "모든 리뷰를 조회합니다.")
	@GetMapping("")
	public ResponseEntity<List<Review>> findAll() {
		return new ResponseEntity<List<Review>>(reviewService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "리뷰 세부 조회", notes = "리뷰 ID에 맞는 리뷰 내용을 조회합니다.")
	@GetMapping("/detail")
	public ResponseEntity<Review> findByReview(Long reviewId) {
		return new ResponseEntity<Review>(reviewService.findByReview(reviewId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저ID로 리뷰 조회", notes = "유저ID에 맞는 리뷰를 조회합니다.")
	@GetMapping("/user")
	public ResponseEntity<List<Review>> findByUser(String userId) {
		return new ResponseEntity<List<Review>>(reviewService.findByUser(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "레시피ID로 리뷰 조회", notes = "레시피 ID에 맞는 리뷰를 조회합니다.")
	@GetMapping("/recipe")
	public ResponseEntity<List<Review>> findByRecipe(Long recipeId) {
		return new ResponseEntity<List<Review>>(reviewService.findByRecipe(recipeId), HttpStatus.OK);
	}
	
	

}
