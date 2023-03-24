package com.springMongoDBLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springMongoDBLogin.domain.AuthenticationResponseBody;
import com.springMongoDBLogin.domain.User;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/login")
	public AuthenticationResponseBody authenticate(@RequestBody User user,HttpSession session) {

		// uj auth token letrehozasa
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
				user.getPassword());

		// token authentikalasa
		Authentication authenticationResult = authManager.authenticate(token);

		//session elinditas --> setAttribute + username
		if (authenticationResult.isAuthenticated()) {
			System.out.println("user is authenticated");
	        session.setAttribute("username", user.getUsername());
			return new AuthenticationResponseBody(true);
		}else {
			return new AuthenticationResponseBody(false);
		}

	}
}
