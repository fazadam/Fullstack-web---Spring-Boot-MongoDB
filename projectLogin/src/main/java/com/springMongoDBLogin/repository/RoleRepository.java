package com.springMongoDBLogin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springMongoDBLogin.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

	Role findByName(String name);
	
}
