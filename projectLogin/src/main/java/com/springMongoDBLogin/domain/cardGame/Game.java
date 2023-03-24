package com.springMongoDBLogin.domain.cardGame;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.springMongoDBLogin.domain.User;

@Document
public class Game {

	@Id
	private String id;
	private List<User> players = new ArrayList<>();
	private Deck deck;
	private boolean isOver;
	
	public Game(String id, List<User> players, Deck deck, boolean isOver) {
		super();
		this.id = id;
		this.players = players;
		this.deck = deck;
		this.isOver = isOver;
	}

	public Game() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}
	
	
}
