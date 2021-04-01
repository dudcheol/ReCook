package com.web.project.controller.user;

import com.web.project.model.recipe.Recipe;
import com.web.project.model.user.LoginGoogleRequest;
import com.web.project.model.user.LoginRequest;
import com.web.project.model.user.SignupRequest;
import com.web.project.model.user.UpdateRequest;
import com.web.project.model.user.User;
import com.web.project.service.user.UserService;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "User", description = "사용자  API")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	public ResponseEntity<User> signup(@Valid @RequestBody SignupRequest request) {

		User user = userService.findUserByUserEmail(request.getUserEmail());

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		user = userService.signup(request);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/login/google")
	@ApiOperation(value = "구글 로그인", notes="회원인 경우 로그인, 아닌 경우 회원 가입")
	public ResponseEntity<Map<String, Object>> loginGoogle(@RequestBody LoginGoogleRequest loginGoogleRequest) {
		User googleUser = userService.findUserByUserGid(loginGoogleRequest.getUserGid());
		
		// 회원이 이미 있는 경우 => 로그인으로
		if(googleUser != null) {
			return userService.loginGoogle(loginGoogleRequest);
		}
		
		// 회원이 없는 경우 => 가입 후 로그인으로
		googleUser = userService.signupGoogle(loginGoogleRequest);
		return userService.loginGoogle(loginGoogleRequest);
	}

	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest);
	}

//	@GetMapping("/mypage")
//	@ApiOperation(value = "마이페이지")
//	public ResponseEntity<Map<String, Object>> mypage(HttpServletRequest request) {
//		return userService.mypage(request);
//	}
	
//	@GetMapping("/mypage/{userId}")
//	@ApiOperation(value = "마이페이지")
//	public ResponseEntity<Map<String, Object>> mypage(@PathVariable("userId") String userId) {
//		return userService.mypage(userId);
//	}
	
	@GetMapping("/mypage/{userName}")
	@ApiOperation(value = "마이페이지")
	public ResponseEntity<Map<String, Object>> mypage(@PathVariable("userName") String userName) {
		return userService.mypage(userName);
	}

	@PutMapping("/update/{userId}")
	@ApiOperation(value = "정보 수정하기")
	public ResponseEntity<Map<String, Object>> update(@RequestBody UpdateRequest updateRequest,
			@PathVariable("userId") String userId) {
		return userService.update(updateRequest, userId);
	}

	@DeleteMapping("/delete/{userId}")
	@ApiOperation(value = "정보 삭제하기")
	public void delete(@PathVariable("userId") String userId) {
		userService.delete(userId);
	}
	
	@GetMapping("/like/{userId}")
	@ApiOperation(value = "유저가 좋아요 누른 레시피 목록 가져오기")
	public ResponseEntity<List<Recipe>> userLike(@PathVariable("userId") String userId, Pageable pageable) {
		return userService.userLike(userId, pageable);
	}
}
