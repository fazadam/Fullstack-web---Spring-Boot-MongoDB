package com.springMongoDBLogin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springMongoDBLogin.domain.User;

public interface UserRepository extends MongoRepository<User, String>{
	User findByUsername(String username);
}
