package com.web.project.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.project.model.user.User;

@Repository
public interface UserDao extends JpaRepository<User, String>{
	
	User findUserByUserEmail(String userEmail);
	User findUserByUserEmailAndUserPassword(String userEmail, String userPassword);
	User findUserByUserId(String userId);
	User findUserByUserGid(String userGid);
	
	Optional<User> findUserOptionalByUserId(String userId);
}
