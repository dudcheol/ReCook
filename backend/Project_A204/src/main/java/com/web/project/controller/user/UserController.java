package com.web.project.controller.user;

import com.web.project.dao.user.UserDao;
import com.web.project.model.BasicResponse;
import com.web.project.model.user.SignupRequest;
import com.web.project.model.user.User;
import com.web.project.service.user.JwtService;

import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/hr")
public class UserController {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDao userDao;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@Valid @RequestBody SignupRequest request) {
		
		ResponseEntity response = null;
		HttpStatus status = null;
		final BasicResponse result = new BasicResponse();
		
		Optional<User> userEmailOpt = userDao.findUserByUserEmail(request.getUserEmail());

		try {
			// 이메일 중복 확인
			if(userEmailOpt.isPresent()) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}else {
				result.status = true;
				result.data = "success";
				
				User user = new User();
				// 1. USER_ID => 13자리 랜덤 수 부여
				user.setUserId(certified_key());
				// 2. 이메일
				user.setUserEmail(request.getUserEmail());
				// 3. 이름
				user.setUserName(request.getUserName());
				// 4. 비밀번호
				user.setUserPassword(request.getUserPassword());
				
				result.object = userDao.save(user);
				status = HttpStatus.OK;
			}
		} catch (RuntimeException e) {
            logger.error("정보조회 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
		
		return new ResponseEntity<>(result, status);
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
}
