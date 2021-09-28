package com.bofigo.rowmaterial.securiy.authentication;

import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.bofigo.rowmaterial.dao.model.UserModel;
import com.bofigo.rowmaterial.domain.service.user.UserService;
import com.bofigo.rowmaterial.securiy.model.JWTAuthenticationToken;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	private JwtUtil jwtUtil;
	private JwtUserDetailService jwtUserDetailService;
	private UserService userService;
	private Queue<String> blackList = new CircularFifoQueue<String>(100);

	public JwtAuthenticationTokenFilter(JwtUtil jwtUtil, JwtUserDetailService jwtUserDetailService,
			UserService userService) {
		this.jwtUtil = jwtUtil;
		this.jwtUserDetailService = jwtUserDetailService;
		this.userService = userService;
	}
	
	public Queue<String> getBlackList() {
		return blackList;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		Cookie cookie = WebUtils.getCookie(httpServletRequest, JwtUtil.JWT_TOKEN);

		
		if (cookie != null) {

			logger.info("cookie: " + cookie.getName());
			
			String jwtToken = cookie.getValue();
			
			if (!blackList.contains(jwtToken)) {
				SecurityContext securityContext = SecurityContextHolder.getContext();

				if (securityContext.getAuthentication() == null) {
					try {
						UserModel userModel = jwtUtil.getUserModelFromToken(jwtToken);
						SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userModel.getRole());
						JWTAuthenticationToken authenticationToken = new JWTAuthenticationToken(userModel, jwtToken,
								Arrays.asList(simpleGrantedAuthority));
						securityContext.setAuthentication(authenticationToken);
						logger.info("auth is setted.");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}else {
				logger.info("jwtToken is in blacklist");
			}
			
		}else {
			logger.info("cookie is null");
		}
		filterChain.doFilter(request, response);

	}

}
