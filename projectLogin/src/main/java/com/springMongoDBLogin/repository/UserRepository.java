package com.springMongoDBLogin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springMongoDBLogin.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String string);
	
	User findByEmail(String string);
	
	
	List<User> findAll();

}
