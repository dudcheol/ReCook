package com.web.project.service.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.web.project.model.user.LoginRequest;
import com.web.project.model.user.SignupRequest;
import com.web.project.model.user.UpdateRequest;
import com.web.project.model.user.User;

public interface UserService {
	
	public User signup(SignupRequest request);
	
	public ResponseEntity<Map<String, Object>> login(LoginRequest loginRequest);
	
	public ResponseEntity<Map<String, Object>> mypage(HttpServletRequest request);
	
	public ResponseEntity<Map<String, Object>> update(UpdateRequest updateRequest, String userId);
	
	public void delete(String userId);
	
	public User findByUserId(String userId);
	
	public User findUserByUserEmail(String userEmail);
	
	public User findUserByUserEmailAndUserPassword(String userEmail, String userPassword);
	
}
