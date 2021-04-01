package com.web.project.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.project.dao.recipe.RecipeDao;
import com.web.project.dao.recipe.RecipeLikeDao;
import com.web.project.dao.user.UserDao;
import com.web.project.model.recipe.Recipe;
import com.web.project.model.recipe.RecipeLike;
import com.web.project.model.user.LoginGoogleRequest;
import com.web.project.model.user.LoginRequest;
import com.web.project.model.user.SignupRequest;
import com.web.project.model.user.UpdateRequest;
import com.web.project.model.user.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RecipeDao recipeDao;
	
	@Autowired RecipeLikeDao recipeLikeDao;

	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User signup(SignupRequest request) {
		User user = new User();

		try {
			// 1. USER_ID => 13자리 랜덤 수 부여
			user.setUserId(certified_key());
			// 2. 이메일
			user.setUserEmail(request.getUserEmail());
			// 3. 이름
			user.setUserName(request.getUserName());
			// 4. 비밀번호
			user.setUserPassword(request.getUserPassword());
		} catch (RuntimeException e) {
			logger.error("회원가입 실패 : {}", e);
		}

		return userDao.save(user);
	}

	@Override
	public User signupGoogle(LoginGoogleRequest request) {
		User user = new User();

		try {
			// 1. USER_ID => 13자리 랜덤 수 부여
			user.setUserId(certified_key());
			// 2. 이메일
			user.setUserEmail(request.getUserEmail());
			// 3. 이름
			user.setUserName(request.getUserName());
			// 4. 비밀번호
			user.setUserPassword(request.getUserGid());
			// 5. GID
			user.setUserGid(request.getUserGid());
			// 6. 이미지
			user.setUserImage(request.getUserImage());
		} catch (RuntimeException e) {
			logger.error("회원가입 실패 : {}", e);
		}

		return userDao.save(user);
	}

	// 13자리 인증키 만들어주는 Method
	private String certified_key() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		int num = 0;

		sb.append('u');
		do {
			num = random.nextInt(75) + 48;
			// 숫자, 대문자, 소문자
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else
				continue;
		} while (sb.length() < 13);

		return sb.toString();
	}

	@Override
	public ResponseEntity<Map<String, Object>> login(LoginRequest loginRequest) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		User user = userDao.findUserByUserEmailAndUserPassword(loginRequest.getUserEmail(),
				loginRequest.getUserPassword());

		try {
			if (user != null) {
				String token = jwtService.create(user);
				resultMap.put("auth-token", token);
				logger.trace("로그인 토큰 정보 : {}", token);
				// USER ID (KEY)
				resultMap.put("user-id", user.getUserId());
				// 이메일
				resultMap.put("user-email", user.getUserEmail());
				// 이름
				resultMap.put("user-name", user.getUserName());
				// 비밀번호
				resultMap.put("user-password", user.getUserPassword());
				// 이미지
				resultMap.put("user-image", user.getUserImage());
				// 소개글
				resultMap.put("user-introduce", user.getUserIntroduce());

				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> loginGoogle(LoginGoogleRequest loginGoogleRequest) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		User user = userDao.findUserByUserEmailAndUserPassword(loginGoogleRequest.getUserEmail(),
				loginGoogleRequest.getUserGid());

		try {
			if (user != null) {
				String token = jwtService.create(user);
				resultMap.put("auth-token", token);
				logger.trace("로그인 토큰 정보 : {}", token);
				// USER ID (KEY)
				resultMap.put("user-id", user.getUserId());
				// 이메일
				resultMap.put("user-email", user.getUserEmail());
				// 이름
				resultMap.put("user-name", user.getUserName());
				// 비밀번호
				resultMap.put("user-password", user.getUserPassword());
				// 이미지
				resultMap.put("user-image", user.getUserImage());
				// 소개글
				resultMap.put("user-introduce", user.getUserIntroduce());
				// GID
				resultMap.put("user-gid", user.getUserGid());

				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> mypage(String userName) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			User user = userDao.findUserByUserName(userName);

			resultMap.put("userName", user.getUserName());
			resultMap.put("userImage", user.getUserImage());
			resultMap.put("userIntroduce", user.getUserIntroduce());

			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Override
	public ResponseEntity<Map<String, Object>> update(UpdateRequest updateRequest, String userId) {
		// 토큰에 저장되어 있는 정보를 가져올 map
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			Optional<User> userOpt = userDao.findUserOptionalByUserId(userId);

			userOpt.ifPresent(selectUser -> {
				selectUser.setUserName(updateRequest.getUserName());
				selectUser.setUserPassword(updateRequest.getUserPassword());
				selectUser.setUserImage(updateRequest.getUserImage());
				selectUser.setUserIntroduce(updateRequest.getUserIntroduce());

				userDao.save(selectUser);
			});

			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			logger.error("정보 수정 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Override
	public void delete(String userId) {
		try {
			Optional<User> userOpt = userDao.findUserOptionalByUserId(userId);

			// DELETE(D)
			userOpt.ifPresent(selectUser -> {
				userDao.delete(selectUser);
			});

		} catch (RuntimeException e) {
			logger.error("정보 삭제 실패 : {}", e);
		}
	}

	@Override
	public ResponseEntity<List<Recipe>> userLike(String userId, Pageable pageable) {
		List<Recipe> resultList = new ArrayList<Recipe>();
		HttpStatus status = null;

		try {
			Page<RecipeLike> page = recipeLikeDao.findAllByUserId(userId, pageable);
			
			Iterator<RecipeLike> iterator = page.iterator();
			while(iterator.hasNext()) {
				RecipeLike recipeLike = iterator.next();
				resultList.add(recipeDao.findRecipeByRecipeId(recipeLike.getRecipeId()));
			}

			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			logger.error("정보 수정 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<List<Recipe>> (resultList, status);
	}

	@Override
	public User findUserByUserId(String userId) {
		return userDao.findUserByUserId(userId);
	}

	@Override
	public User findUserByUserEmail(String userEmail) {
		return userDao.findUserByUserEmail(userEmail);
	}

	@Override
	public User findUserByUserEmailAndUserPassword(String userEmail, String userPassword) {
		return userDao.findUserByUserEmailAndUserPassword(userEmail, userPassword);
	}

	@Override
	public User findUserByUserGid(String userGid) {
		return userDao.findUserByUserGid(userGid);
	}

}
