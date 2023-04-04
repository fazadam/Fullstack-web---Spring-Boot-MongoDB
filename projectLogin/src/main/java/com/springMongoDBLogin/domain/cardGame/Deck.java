package com.springMongoDBLogin.domain.cardGame;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Deck {

    private final String name;
    private final List<Card> cards;

    public Deck(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
	
}
