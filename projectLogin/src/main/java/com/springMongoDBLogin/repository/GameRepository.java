package com.springMongoDBLogin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springMongoDBLogin.domain.cardGame.Game;

public interface GameRepository extends MongoRepository<Game,String>{

}
