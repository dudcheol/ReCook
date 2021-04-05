package com.web.project.controller.review;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@ApiOperation(value = "리뷰 작성", notes = "한장의 사진과 내용을 작성합니다.")
	@PostMapping("/create")
	public ResponseEntity<Review> createReview(ReviewUpload reviewUpload) {
		return reviewService.createReview(reviewUpload);
	}
	
	@ApiOperation(value = "모든 리뷰 조회", notes = "모든 리뷰를 최신순으로 조회합니다.")
	@GetMapping("/all")
	public ResponseEntity<Page<Review>> findAll(Pageable pageable) {
		return reviewService.findAll(pageable);
	}
	
	@ApiOperation(value = "모든 리뷰 중 사진 있는 것만 조회", notes = "모든 리뷰 중 사진 있는 것만 최신순으로 조회합니다.")
	@GetMapping("/all/image")
	public ResponseEntity<Page<Review>> findAllImage(Pageable pageable) {
		return reviewService.findAllImage(pageable);
	}
	
	@ApiOperation(value = "리뷰 세부 조회", notes = "리뷰 ID에 맞는 리뷰 내용을 조회합니다.")
	@GetMapping("/detail/{reviewId}")
	public ResponseEntity<Map<String, Object>> findByReview(@PathVariable("reviewId") int reviewId) {
		return reviewService.findByReview(reviewId);
	}
	
//	@ApiOperation(value = "유저ID로 리뷰 조회", notes = "유저ID에 맞는 리뷰를 조회합니다.")
//	@GetMapping("/user/{userId}")
//	public ResponseEntity<List<Review>> findByUser(@PathVariable("userId") String userId) {
//		return reviewService.findByUser(userId);
//	}
	
	@ApiOperation(value = "유저 이름으로 리뷰 조회", notes = "유저 이름에 맞는 리뷰를 조회합니다.")
	@GetMapping("/user/{userName}")
	public ResponseEntity<List<Review>> findByUser(@PathVariable("userName") String userName) {
		return reviewService.findByUser(userName);
	}
	
	@ApiOperation(value = "레시피 SUB ID로 리뷰 조회", notes = "레시피 SUB ID에 맞는 리뷰를 조회합니다.")
	@GetMapping("/recipe/{recipeSubId}")
	public ResponseEntity<List<Review>> findByRecipe(@PathVariable("recipeSubId") int recipeSubId) {
		return reviewService.findByRecipe(recipeSubId);
	}
	
}
