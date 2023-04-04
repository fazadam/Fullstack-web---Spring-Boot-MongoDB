package com.springMongoDBLogin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.domain.cardGame.Card;
import com.springMongoDBLogin.repository.CardRepository;

@Service
public class GameService {

	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private UserDetailsServiceImpl userService;

	@Autowired
	private User player;

	private List<Card> playerHand;
	private List<Card> playerDeck;

//    public GameService(User player) {
//        this.playerDeck = player.getAllCards();
//        this.playerHand = drawCardsFromDeck(10);
//    }

	// constructor mindenhez
	public GameService(BoardService boardService, UserDetailsServiceImpl userService, User player,
			List<Card> playerHand, List<Card> playerDeck) {
		super();
		this.boardService = boardService;
		this.userService = userService;
		this.player = player;
		this.playerHand = playerHand;
		this.playerDeck = playerDeck;
	}

	//vegye ki a user kartyait,
	//random szam alapjan vegyen ki belole 10et
	//adja at a playerHand-nak
	//torolje a playerDeckbol
	public List<Card> drawCardsFromDeck(String username, String deckname) {
		User user =  (User) userService.loadUserByUsername(username);
	    Map<String, List<String>> userDecks = user.getUserDecks();
	    
	    // Check if the deck exists in the user's deck map
	    if (!userDecks.containsKey(deckname)) {
	        throw new RuntimeException("No deck with name: " + deckname);
	    }
	    
	    this.playerDeck = new ArrayList<>();
	    for (String cardName : userDecks.get(deckname)) {
	        Card card = cardRepository.findByName(cardName);
	        if (card == null) {
	            throw new RuntimeException("Card not found: " + cardName);
	        }
	        this.playerDeck.add(card);
	        System.out.println(card.getName());
//	        System.out.println(this.playerDeck);
	    }
	    		
		//ez azert kell, mert kulonben mindig csak hozzaadodik ujabb 10 kartya az elozokhoz --> igy minden alkalommal uj ures lista lesz
		this.playerHand = new ArrayList<>();

		Random randomCardFromDeck = new Random();
		for (int i = 0; i < 10; i++) {
			if (playerDeck.isEmpty()) {
				break;
			}
			int randomNumber = randomCardFromDeck.nextInt(playerDeck.size());
			Card card = playerDeck.get(randomNumber);
			playerDeck.remove(card);

			playerHand.add(card);
			System.out.println(card.getName());

		}
        System.out.println(this.playerHand);
        System.out.println("playerDeck size : " + playerDeck.size());
		System.out.println(playerHand.size());

		return playerHand;

	}

	//nezze meg a playerHand-ben az indexhez tartozo kartyat
	//adja hozza a megfelelo sorhoz a kartyat -> boardservice.addcardtotherow
	public Card playCard(int cardToPlayIndex,String row) {

		Card card = playerHand.get(cardToPlayIndex);

		if(playerHand.isEmpty()) {
			System.out.println("ures a user keze");
			return null;
		}
		
		
		System.out.println(cardToPlayIndex);
		System.out.println(playerHand.get(cardToPlayIndex));
		System.out.println(playerHand.size());
		System.out.println(playerHand);
		
		
		//HA NEM KELL A BOARDSERVICE ES A SOROK AKKOR EZ SEM
		//boardService.addCardToTheRow(card, row);
		
		//A PLAYERHAND SEM KELL MERT AZ ANGULAR HANDSCARD INTEZI??????
		playerHand.remove(card);
		setPlayerHand(playerHand);
		
		return card;
	}

	
	
	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public List<Card> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}

	public List<Card> getPlayerDeck() {
		return playerDeck;
	}

	public void setPlayerDeck(List<Card> playerDeck) {
		this.playerDeck = player.getAllCards();
	}

}
