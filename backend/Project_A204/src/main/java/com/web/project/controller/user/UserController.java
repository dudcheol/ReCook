package com.web.project.controller.user;

import com.web.project.model.user.LoginRequest;
import com.web.project.model.user.SignupRequest;
import com.web.project.model.user.UpdateRequest;
import com.web.project.model.user.User;
import com.web.project.service.user.UserService;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest);
	}

	@GetMapping("/mypage")
	@ApiOperation(value = "마이페이지")
	public ResponseEntity<Map<String, Object>> mypage(HttpServletRequest request) {
		return userService.mypage(request);
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
}
