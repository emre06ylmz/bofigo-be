package com.bofigo.rowmaterial.securiy.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.bofigo.rowmaterial.api.controller.CurrencySettingsController;
import com.bofigo.rowmaterial.dao.model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtUtil {

	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	private static final int JWT_TOKEN_VALIDIY = 5 * 60 * 60;

	public static final String JWT_TOKEN = "BOFIGO_JWT_TOKEN";

	// TODO
	private String secret = "bofigo";

	public String generateToken(UserModel userModel) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("name", userModel.getUsername());
		claims.put("role", userModel.getRole());
		return doGenerateToken(claims, userModel.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDIY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public UserModel getUserModelFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		String name = claims.get("name").toString();
		String username = getUserCodeFromToken(claims);
		String role = claims.get("role").toString();

		UserModel userModel = new UserModel();
		userModel.setName(name);
		userModel.setUsername(username);
		userModel.setRole(role);

		return userModel;
	}

	private String getUserCodeFromToken(Claims claims) {
		return getClaimsValue(claims, Claims::getSubject);
	}

	private <T> T getClaimsValue(Claims claims, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

}
