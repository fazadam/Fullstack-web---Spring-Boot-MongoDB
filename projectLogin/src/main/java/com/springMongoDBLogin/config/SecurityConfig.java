package com.springMongoDBLogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springMongoDBLogin.domain.User;




@Configuration
@EnableMethodSecurity 
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	//kell h a gameservice-ben meg tudjam hivni mint player
    @Bean
    public User user() {
        return new User();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeHttpRequests()

				//.requestMatchers("/admin").hasRole("ADMIN")
				//.requestMatchers("/user").hasRole("USER")
				.requestMatchers("/registration").permitAll()
				.requestMatchers("/cards").authenticated()
				//.requestMatchers("/reg").permitAll()
				//.requestMatchers("/cards").permitAll()
				.anyRequest().permitAll();
//	
//				.and()
//			.formLogin().permitAll()
//				.loginPage("/login")
//				.loginProcessingUrl("/login")
//				.defaultSuccessUrl("/", true)
//				.and()
//			.logout()
//				.logoutSuccessUrl("/login?logout")
//				.permitAll();

		return http.build();
	}

}
