package com.springMongoDBLogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{

	
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests()
				.requestMatchers("/signup").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login.html")
				.permitAll();
	}

	
}
