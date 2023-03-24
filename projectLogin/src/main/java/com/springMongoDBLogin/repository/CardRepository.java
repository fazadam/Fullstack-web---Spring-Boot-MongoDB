package com.springMongoDBLogin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springMongoDBLogin.domain.cardGame.Card;

public interface CardRepository extends MongoRepository<Card,String>{
	
	List<Card> findAll();
	
	Card findByName(String name);
	
	//List<Card> saveAll(List<Card> cards);
}
