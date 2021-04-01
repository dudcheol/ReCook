package com.web.project.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.web.project.model.recipe.Recipe;
import com.web.project.model.user.LoginGoogleRequest;
import com.web.project.model.user.LoginRequest;
import com.web.project.model.user.SignupRequest;
import com.web.project.model.user.UpdateRequest;
import com.web.project.model.user.User;

public interface UserService {
	
	public User signup(SignupRequest request);
	
	public User signupGoogle(LoginGoogleRequest request);
	
	public ResponseEntity<Map<String, Object>> login(LoginRequest loginRequest);
	
	public ResponseEntity<Map<String, Object>> loginGoogle(LoginGoogleRequest loginGoogleRequest);
	
	public ResponseEntity<Map<String, Object>> mypage(String userName);
	
	public ResponseEntity<Map<String, Object>> update(UpdateRequest updateRequest, String userId);
	
	public void delete(String userId);
	
	public ResponseEntity<List<Recipe>> userLike(String userId, Pageable pageable);
	
	public User findUserByUserId(String userId);
	
	public User findUserByUserGid(String userGid);
	
	public User findUserByUserEmail(String userEmail);
	
	public User findUserByUserEmailAndUserPassword(String userEmail, String userPassword);
	
}
