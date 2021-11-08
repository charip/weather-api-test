package com.weather.api.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
@Order(1)
public class AuthConfiguration extends WebSecurityConfigurerAdapter {
	private static final String API_KEY_AUTH_HEADER_NAME = "API_KEY";
	private static final List<String> API_KEY_VALUES = Collections.unmodifiableList
			(Arrays.asList("apiKey1","apiKey2","apiKey3","apiKey4","apiKey5"));
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		ApiKeyAuthFilter filter = new ApiKeyAuthFilter(API_KEY_AUTH_HEADER_NAME);
		filter.setAuthenticationManager(new AuthenticationManager() {

			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String principal = (String) authentication.getPrincipal();
				if (!API_KEY_VALUES.contains(principal))
				{
					throw new BadCredentialsException("The API key was not found or not the expected value.");
				}
				authentication.setAuthenticated(true);
				return authentication;
			}
		});
		httpSecurity.
		antMatcher("/weather/**").
		csrf().disable().
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().addFilter(filter).addFilterBefore(new ExceptionTranslationFilter(
				new Http403ForbiddenEntryPoint()), 
				filter.getClass()
				).authorizeRequests().anyRequest().authenticated();
	}


}
