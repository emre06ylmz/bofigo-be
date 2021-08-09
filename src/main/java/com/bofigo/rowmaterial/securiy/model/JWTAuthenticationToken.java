package com.bofigo.rowmaterial.securiy.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.bofigo.rowmaterial.dao.model.UserModel;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

	private UserModel userModel;
	private String token;

	public JWTAuthenticationToken(UserModel userModel, String token,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);

		this.userModel = userModel;
		this.token = token;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return userModel;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
