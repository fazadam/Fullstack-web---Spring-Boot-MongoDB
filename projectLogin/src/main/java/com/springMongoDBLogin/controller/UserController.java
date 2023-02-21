package com.springMongoDBLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.service.UserDetailsServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserDetailsServiceImpl userService;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "AdminPage";
	}

	@RequestMapping("/user")
	public String user() {
		return "UserPage";
	}

	@RequestMapping("/login")
	public String login() {
		return "/login";
	}

	@RequestMapping("/registration")
	public String registration(User user) {
		User User = new User(user.getUsername(), user.getPassword(), user.getEmail());
		return "/registration";
	}

	@PostMapping("/reg")
	public String reg(User user) {
		userService.registerUser(user);
		return "/login";
	}

}
