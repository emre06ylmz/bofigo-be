package com.bofigo.rowmaterial.securiy.authentication;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.dao.repository.UserRepository;
import com.bofigo.rowmaterial.securiy.model.JWTAuthenticationToken;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;
import com.bofigo.rowmaterial.util.MockUtil;

@Component
public class AuthenticationProviderService implements AuthenticationProvider {

	private UserRepository userRepository;
	private JwtUtil jwtUtil;

	public AuthenticationProviderService(UserRepository userRepository, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
		
		UserModel userModel = MockUtil.mockUser();
		if(userRepository.findByUsername(userModel.getUsername()) == null) {
			userRepository.save(userModel);
		}
		
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		//TODO
		username = "emre";
		password = "emre";
		
		boolean isAuthenticated = true;

		if (isAuthenticated) {
			UserModel userModel = userRepository.findByUsername(username);

			if(userModel.getPassword().equals(password)) {
				final String token = jwtUtil.generateToken(userModel);
				SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userModel.getRole());
				JWTAuthenticationToken jwtAuthenticationToken = new JWTAuthenticationToken(userModel, token,
						Arrays.asList(simpleGrantedAuthority));
				return jwtAuthenticationToken;
			}else {
				throw new UsernameNotFoundException("kullanıcı veya şifre yanlış");
			}
		}

		throw new UsernameNotFoundException("kullanıcı bulunamadı");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
