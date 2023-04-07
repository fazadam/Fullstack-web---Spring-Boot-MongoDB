package com.springMongoDBLogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springMongoDBLogin.domain.cardGame.Card;
import com.springMongoDBLogin.domain.cardGame.Game;
import com.springMongoDBLogin.domain.cardGame.GamePlayer;
import com.springMongoDBLogin.repository.GameRepository;
import com.springMongoDBLogin.service.BoardService;
import com.springMongoDBLogin.service.GameService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

	@Autowired
	private GameService gameService;
	@Autowired
	private GameRepository gameRepository;

	@GetMapping("/draw/{username}/{deckname}")
	public List<Card> drawCards(@PathVariable String username, @PathVariable String deckname) {
		return gameService.drawCardsFromDeck(username, deckname);
	}

//	@PostMapping("/playCard/{row}/{index}")
//	public void playCard(@PathVariable String row, @PathVariable int index) {
//		gameService.playCard(index, row);
//	}

	@PostMapping("/createNewGame/{gameName}/{password}")
	public ResponseEntity<String> createGame(@PathVariable String gameName, @PathVariable String password) {

		try {
			gameService.createNewGame(gameName, password);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/playCard/{gameName}")
	public ResponseEntity<String> playCard(@PathVariable String gameName,
			@RequestBody GamePlayer gamePlayer) {
		try {
			gameService.playCard(gameName, gamePlayer);
            System.out.println("kliensrol jovo deck meret: " + gamePlayer.getCurrentDeckCards().size());

			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/getGameStatus/{gameName}")
	public Game giveBackGameByName(@PathVariable String gameName) {
		return gameService.giveBackGameByName(gameName);
	
	}
	
	@GetMapping("/listOfAllGames")
	public List<Game> listOfAllGames (){

		return gameRepository.findAll();
	}
	
	@GetMapping("/{gameName}/play")
	public ResponseEntity<String> playGame(@PathVariable String gameName) {
	    try {
	        return ResponseEntity.ok(gameService.playGame(gameName));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	}
    
    @PutMapping("/{gameName}/deleteActiveCardsForNextRound")
    public ResponseEntity<String> deleteActiveCardsForNextRound(@PathVariable String gameName) {
        gameService.deleteActiveCardsForNextRound(gameName);
        return ResponseEntity.ok("Active cards deleted for next round in game: " + gameName);
    }

}
