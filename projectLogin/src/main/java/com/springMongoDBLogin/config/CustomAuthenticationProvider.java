package com.springMongoDBLogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springMongoDBLogin.service.UserDetailsServiceImpl;

//AuthenticationProvider implementacio
@Component
@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// user from userdetailserviceimpl => getPrincipal kiveszi a username-et
		String providedUsername = authentication.getPrincipal().toString();
		UserDetails user = userDetailsServiceImpl.loadUserByUsername(providedUsername);
		//System.out.println("User Details from UserService based on username-" + providedUsername + " : " + user);

		// getCredentials kiveszi tostring-be a jelszot
		String providedPassword = authentication.getCredentials().toString();
		String correctPassword = user.getPassword();
		//System.out.println("Provided Password - " + providedPassword + " Correct Password: " + correctPassword);

		// jelszo ellenorzes
		if (!encoder.matches(providedPassword, correctPassword))
			throw new RuntimeException("incorrect password" + " " + encoder.matches(providedPassword, correctPassword));

		// adjon vissza Authentication Object-et
		Authentication authenticationResult = new UsernamePasswordAuthenticationToken(user,
				authentication.getCredentials(), user.getAuthorities());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
