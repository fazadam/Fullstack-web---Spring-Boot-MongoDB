package com.springMongoDBLogin.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springMongoDBLogin.domain.cardGame.Game;
import com.springMongoDBLogin.domain.cardGame.GamePlayer;

public interface GameRepository extends MongoRepository<Game,String>{

	Game findByGameName(String gameName);
	
	List<Game> findAll();
	


}
