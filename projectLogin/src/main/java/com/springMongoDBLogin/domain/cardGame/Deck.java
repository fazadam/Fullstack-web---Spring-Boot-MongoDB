package com.springMongoDBLogin.domain.cardGame;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Deck {

	private List<Card> cards = new ArrayList<>();
	
	public Deck(List<Card> cards) {
		super();
		this.cards = cards;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	
}
