package com.bofigo.rowmaterial.securiy.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bofigo.rowmaterial.constant.ApplicationConstants;
import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.securiy.model.JWTAuthenticationToken;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@RestController
@RequestMapping(ApplicationConstants.ROUTE_AUTHENTICATION)
public class AuthenticationController {

	private JwtUtil jwtUtil;

	public AuthenticationController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("user")
	public ResponseEntity<UserModel> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof JWTAuthenticationToken) {
			JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) authentication;
			UserModel userModel = jwtUtil.getUserModelFromToken(jwtAuthenticationToken.getToken());
			return ResponseEntity.ok().body(userModel);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserModel());
	}

}
