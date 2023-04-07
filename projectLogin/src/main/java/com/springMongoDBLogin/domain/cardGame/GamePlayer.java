package com.springMongoDBLogin.domain.cardGame;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GamePlayer {
	
	@Id
	private String playerName;
	
	private List<Card> currentDeckCards;
	
	private Card activeCard;

	private int gamePoints;
	
	private int totalWins;
	
	

	public GamePlayer(String playerName,List<Card> currentDeckCards, Card activeCard, int gamePoints, int totalWins) {
		super();
		this.playerName  = playerName;
		this.currentDeckCards = currentDeckCards;
		this.activeCard = activeCard;
		this.gamePoints = gamePoints;
		this.totalWins = totalWins;
	}

	public List<Card> getCurrentDeckCards() {
		return currentDeckCards;
	}

	public void setCurrentDeckCards(List<Card> currentDeckCards) {
		this.currentDeckCards = currentDeckCards;
	}

	public Card getActiveCard() {
		return activeCard;
	}

	public void setActiveCard(Card activeCard) {
		this.activeCard = activeCard;
	}

	public int getGamePoints() {
		return gamePoints;
	}

	public void setGamePoints(int gamePoints) {
		this.gamePoints = gamePoints;
	}

	public int getTotalWins() {
		return totalWins;
	}

	public void setTotalWins(int totalWins) {
		this.totalWins = totalWins;
	}
	
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}	

}
