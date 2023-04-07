package com.springMongoDBLogin.domain.cardGame;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Game {

	@Id
	private String id;
	
	private String gameName;
	
	private String gamePassword;

	private Set<GamePlayer> players;
	
	private String activePlayerName;
	
	
	
	public Game() {
		super();
	}

	public Game(String gameName, String gamePassword, Set<GamePlayer> players, String activePlayerName) {
		super();
		this.gameName = gameName;
		this.gamePassword = gamePassword;
		this.players = players;
		this.activePlayerName = activePlayerName;
	}


	
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGamePassword() {
		return gamePassword;
	}

	public void setGamePassword(String gamePassword) {
		this.gamePassword = gamePassword;
	}

	public Set<GamePlayer> getPlayers() {
		return players;
	}

	public void setPlayers(Set<GamePlayer> players) {
		this.players = players;
	}

	public String getActivePlayerName() {
		return activePlayerName;
	}

	public void setActivePlayerName(String activePlayerName) {
		this.activePlayerName = activePlayerName;
	}



    // check if the game has two players
    public boolean isFull() {
        return this.players.size() >= 2;
    }
	
}
