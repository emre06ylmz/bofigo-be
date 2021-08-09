package com.bofigo.rowmaterial.securiy.authentication;

import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@Component
public class JwtUserDetailService {

	private JwtUtil jwtUtil;
	
	public UserModel getUserModelFromToken(String token) {
		return jwtUtil.getUserModelFromToken(token);
	}
	
}
