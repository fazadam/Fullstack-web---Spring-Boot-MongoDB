package com.springMongoDBLogin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.domain.cardGame.Card;
import com.springMongoDBLogin.domain.cardGame.Game;
import com.springMongoDBLogin.domain.cardGame.GamePlayer;
import com.springMongoDBLogin.repository.CardRepository;
import com.springMongoDBLogin.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private UserDetailsServiceImpl userService;

	@Autowired
	private User player;

	private List<Card> playerHand;
	private List<Card> playerDeck;


	public GameService(BoardService boardService, UserDetailsServiceImpl userService, User player,
			List<Card> playerHand, List<Card> playerDeck) {
		super();
		this.boardService = boardService;
		this.userService = userService;
		this.player = player;
		this.playerHand = playerHand;
		this.playerDeck = playerDeck;
	}

	// create new game
	public void createNewGame(String newGameName, String password) {
		Game game = new Game(newGameName, password, new HashSet<>(), null);
		gameRepository.save(game);
	}

	public void setPlayersBoard(String gameName, GamePlayer gamePlayer) {
		Game game = gameRepository.findByGameName(gameName);

		if (game.getPlayers().size() < 2) {
			boolean playerExists = false;
			for (GamePlayer player : game.getPlayers()) {
				if (player.getPlayerName().equals(gamePlayer.getPlayerName())) {
					playerExists = true;
					break;
				}
			}
			if (!playerExists) {
				game.getPlayers().add(gamePlayer);
				gameRepository.save(game);
			} else {
				throw new RuntimeException("Player already exists in game: " + gamePlayer.getPlayerName());
			}
		} else {
			throw new RuntimeException("Game already has 2 players.");
		}
	}

	public String playCard(String gameName, GamePlayer gamePlayer) {
		Game game = gameRepository.findByGameName(gameName);
		if (game == null) {
			throw new RuntimeException("Game not found: " + gameName);
		}

		//System.out.println(gamePlayer.getCurrentDeckCards());

		boolean playerExists = false;
		for (GamePlayer player : game.getPlayers()) {
			if (player.getPlayerName().equals(gamePlayer.getPlayerName())) {
				playerExists = true;
				if (player.getActiveCard() != null) {
					return "wait for opponents turn";
				}
				player.setActiveCard(gamePlayer.getActiveCard());
				// player.getCurrentDeckCards().remove(gamePlayer.getActiveCard());
				player.setCurrentDeckCards(gamePlayer.getCurrentDeckCards());

				//System.out.println(player.getCurrentDeckCards());
				break;
			}
		}

		if (!playerExists && game.getPlayers().size() < 2) {
			game.getPlayers().add(gamePlayer);
		} else {
			System.out.println(" tul sok jatekos");
		}

		gameRepository.save(game);
		return "card played successfully";
	}

	public Game giveBackGameByName(String gameName) {
		Game game = gameRepository.findByGameName(gameName);
		return game;
	}

	// vegye ki a user kartyait,
	// random szam alapjan vegyen ki belole 10et
	// adja at a playerHand-nak
	// torolje a playerDeckbol
	public List<Card> drawCardsFromDeck(String username, String deckname) {
		User user = (User) userService.loadUserByUsername(username);
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
			// System.out.println(card.getName());
//	        System.out.println(this.playerDeck);
		}

		// ez azert kell, mert kulonben mindig csak hozzaadodik ujabb 10 kartya az
		// elozokhoz --> igy minden alkalommal uj ures lista lesz
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
			// System.out.println(card.getName());

		}
//		System.out.println(this.playerHand);
//		System.out.println("playerDeck size : " + playerDeck.size());
//		System.out.println(playerHand.size());

		return playerHand;

	}

	// nezze meg a playerHand-ben az indexhez tartozo kartyat
	// adja hozza a megfelelo sorhoz a kartyat -> boardservice.addcardtotherow
//	public Card playCard(int cardToPlayIndex, String row) {
//
//		Card card = playerHand.get(cardToPlayIndex);
//
//		if (playerHand.isEmpty()) {
//			System.out.println("ures a user keze");
//			return null;
//		}
//
//		playerHand.remove(card);
//		setPlayerHand(playerHand);
//
//		return card;
//	}

	public String playGame(String gameName) {
		// Find the game object by name
		Game game = gameRepository.findByGameName(gameName);
		if (game == null) {
			throw new IllegalArgumentException("Game not found");
		}

		List<GamePlayer> playersList = new ArrayList<>(game.getPlayers());
		GamePlayer player1 = playersList.get(0);
		GamePlayer player2 = playersList.get(1);

		Card card1 = player1.getActiveCard();
		Card card2 = player2.getActiveCard();

		String type1 = card1.getType();
		String type2 = card2.getType();
		String[] types = { "orc", "human", "istari", "dwarf", "elf" };
		int index1 = Arrays.asList(types).indexOf(type1);
		int index2 = Arrays.asList(types).indexOf(type2);

		if (index1 == -1 || index2 == -1) {
			throw new IllegalArgumentException("Invalid card type");
		}

		int[][] results = { 
				{ 0, 2, 1, 1, 2 }, 
				{ 1, 0, 2, 2, 1 },
				{ 2, 1, 0, 1, 2 }, 
				{ 2, 1, 2, 0, 1 },
				{ 1, 2, 1, 2, 0 } };

		int result = results[index1][index2];

		if (result == 0) {
			return "It's a tie!";
		} else if (result == 1) {
			
	        if (!(player1.getGamePoints() >= 5)) {
			player1.setGamePoints(player1.getGamePoints() + 1);
			gameRepository.save(game);

			return player1.getPlayerName() + " wins! " + type1 + " beats " + type2 + " " + player1.getPlayerName()
					+ " points: " + player1.getGamePoints() + " " + player2.getPlayerName() + " points: "
					+ player2.getGamePoints();
	        } else {
	            return player1.getPlayerName() + " wins the game!";
	        }
		} else {
	        if (!(player2.getGamePoints() >= 5)) {

			player2.setGamePoints(player2.getGamePoints() + 1);
			gameRepository.save(game);

			return player2.getPlayerName() + " wins! " + type2 + " beats " + type1 + " " + player1.getPlayerName()
					+ " points: " + player1.getGamePoints() + " " + player2.getPlayerName() + " points: "
					+ player2.getGamePoints();
	        } else {
	            return player2.getPlayerName() + " wins the game!";
	        }
		}
	}

	public void deleteActiveCardsForNextRound(String gameName) {
		Game game = gameRepository.findByGameName(gameName);
		if (game == null) {
			throw new RuntimeException("Game not found: " + gameName);
		}

		// Iterate over the players list and set activeCard to null
		for (GamePlayer player : game.getPlayers()) {
			player.setActiveCard(null);
		}

		gameRepository.save(game);

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
