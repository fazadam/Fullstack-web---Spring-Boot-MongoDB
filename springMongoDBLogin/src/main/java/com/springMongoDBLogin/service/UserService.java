package com.springMongoDBLogin.service;

import com.springMongoDBLogin.domain.User;

public interface UserService {

	public String signUpUser(User user);
	
	public User findByUsername(String username);
	
	public String userActivation(String code);
}
