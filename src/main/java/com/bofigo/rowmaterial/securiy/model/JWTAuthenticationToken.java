package com.bofigo.rowmaterial.securiy.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.bofigo.rowmaterial.dao.model.UserModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}
