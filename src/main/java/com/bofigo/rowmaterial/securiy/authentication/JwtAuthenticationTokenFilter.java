package com.bofigo.rowmaterial.securiy.authentication;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.domain.service.UserService;
import com.bofigo.rowmaterial.securiy.model.JWTAuthenticationToken;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil;
	private JwtUserDetailService jwtUserDetailService;
	private UserService userService;

	public JwtAuthenticationTokenFilter(JwtUtil jwtUtil, JwtUserDetailService jwtUserDetailService,
			UserService userService) {
		this.jwtUtil = jwtUtil;
		this.jwtUserDetailService = jwtUserDetailService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		Cookie cookie = WebUtils.getCookie(httpServletRequest, JwtUtil.JWT_TOKEN);

		if (cookie != null) {
			String jwtToken = cookie.getValue();
			SecurityContext securityContext = SecurityContextHolder.getContext();

			if (securityContext.getAuthentication() == null) {
				try {
					UserModel userModel = jwtUtil.getUserModelFromToken(jwtToken);
					SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userModel.getRole());
					JWTAuthenticationToken authenticationToken = new JWTAuthenticationToken(userModel, jwtToken,
							Arrays.asList(simpleGrantedAuthority));
					securityContext.setAuthentication(authenticationToken);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		filterChain.doFilter(request, response);

	}

}
