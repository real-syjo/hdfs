package com.src.hdfs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.src.hdfs.config.oauth.PrincipalOauth2UserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled = true) //secured 어노테이션을 활성화, preAuthorize/postAutorize 활성화 
public class SecurityConfig {
	
	private final CustomAuthFailureHandler authfailurehandler = new CustomAuthFailureHandler();
	
	@Autowired
	private PrincipalOauth2UserService principalDetailsService;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() 
			//.antMatchers("/manager/**").access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
				.formLogin()
				.loginPage("/loginForm")
				.loginProcessingUrl("/login") 
				.defaultSuccessUrl("/user")
				.failureHandler(authfailurehandler)
			.and() 
				.oauth2Login()
				.loginPage("/loginForm") 
				.defaultSuccessUrl("/user")
				.userInfoEndpoint()
				.userService(principalDetailsService); 
		
		return http.build();
	}
}