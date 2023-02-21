package com.springMongoDBLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.repository.UserRepository;

@Controller
public class SignUpController {

	@Autowired
	private UserRepository userRepository;

	public User registerNewUserAccount(User user) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

		user.setUsername(user.getUsername());
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEmail(user.getEmail());
		return userRepository.save(user);

		}
	}

