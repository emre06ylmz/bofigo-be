package com.bofigo.rowmaterial.securiy.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.securiy.model.JWTAuthenticationToken;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@RestController
@RequestMapping(ApplicationConstants.ROUTE_AUTHENTICATION)
@CrossOrigin(origins = "https://bofigo-fe.herokuapp.com", allowCredentials = "true")
public class AuthenticationController {

	private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	private JwtUtil jwtUtil;

	public AuthenticationController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("user")
	public ResponseEntity<UserModel> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof JWTAuthenticationToken) {
			logger.info("valid authentication is exist.");
			JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) authentication;
			UserModel userModel = jwtUtil.getUserModelFromToken(jwtAuthenticationToken.getToken());
			logger.info(userModel.toString());
			return ResponseEntity.ok().body(userModel);
		}
		logger.error("UNAUTHORIZED");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserModel());
	}

}
