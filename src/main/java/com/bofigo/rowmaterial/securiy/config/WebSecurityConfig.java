package com.bofigo.rowmaterial.securiy.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bofigo.rowmaterial.BofigoBeApplication;
import com.bofigo.rowmaterial.filter.CookieFilter;
import com.bofigo.rowmaterial.securiy.authentication.AuthenticationProviderService;
import com.bofigo.rowmaterial.securiy.authentication.JwtAuthenticationTokenFilter;
import com.bofigo.rowmaterial.securiy.handler.AuthenticationFailureHandler;
import com.bofigo.rowmaterial.securiy.handler.AuthenticationLogoutSuccessHandler;
import com.bofigo.rowmaterial.securiy.handler.AuthenticationSucessHandler;
import com.bofigo.rowmaterial.securiy.handler.UnAuthorizedEntryPoint;
import com.bofigo.rowmaterial.securiy.util.JwtUtil;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationSucessHandler authenticationSucessHandler;

	@Autowired
	private UnAuthorizedEntryPoint unAuthorizedEntryPoint;

	@Autowired
	private AuthenticationProviderService authenticationProviderService;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private AuthenticationLogoutSuccessHandler authenticationLogoutSuccessHandler;

	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProviderService);
	}

	@Bean
	public RequestMatcher requestMatcher() {
		return new RequestMatcher() {

			@Override
			public boolean matches(HttpServletRequest request) {
				String resource = request.getRequestURI();
				if ((resource.contains("/actuator/") 
						|| resource.contains("swagger") 
						|| resource.contains("/api-docs")
						|| resource.contains("/error") 
						|| resource.contains("login")  
						//|| resource.contains("api") 
						|| resource.contains("/actuator/"))
						|| request.getMethod().equals("OPTIONS")) {
					return false;
				}

				return true;
			}
		};
	}

	protected void configure(HttpSecurity http) throws Exception {

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();

		config.addAllowedOrigin(BofigoBeApplication.FE_ORIGIN);
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		source.registerCorsConfiguration("/**", config);

		http.addFilterAfter(new CookieFilter(), UsernamePasswordAuthenticationFilter.class);

		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unAuthorizedEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.requestMatchers(requestMatcher()).authenticated().and().formLogin()
				.loginProcessingUrl("/api/application/login").successHandler(authenticationSucessHandler)
				.failureHandler(authenticationFailureHandler).usernameParameter("username")
				.passwordParameter("password").and().logout().logoutUrl("/api/application/logout")
				.logoutSuccessHandler(authenticationLogoutSuccessHandler).invalidateHttpSession(true)
				.deleteCookies(JwtUtil.JWT_TOKEN);

		http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
	}

}
