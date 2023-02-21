package com.springMongoDBLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springMongoDBLogin.domain.AuthenticationResponseBody;
import com.springMongoDBLogin.domain.User;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/login")
	public AuthenticationResponseBody authenticate(@RequestBody User user) {

		// uj auth token letrehozasa
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
				user.getPassword());
		System.out.println("\nAuthentication Token Before Authentication: " + token);

		// token authentikalasa
		Authentication authResult = authManager.authenticate(token);
		System.out.println("Authentication Token After Authentication: " + authResult);

		System.out.println(
				"Authentication Token in Security Context: " + SecurityContextHolder.getContext().getAuthentication());

		if (authResult.isAuthenticated())
			System.out.println("user is authenticated");

		return new AuthenticationResponseBody(true);
	}
}
