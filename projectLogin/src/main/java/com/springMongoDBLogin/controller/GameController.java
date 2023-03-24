package com.springMongoDBLogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springMongoDBLogin.domain.cardGame.Card;
import com.springMongoDBLogin.service.BoardService;
import com.springMongoDBLogin.service.GameService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

	@Autowired
	private GameService gameService;
	@Autowired
	private BoardService boardService;

	@GetMapping("/draw/{username}")
	public List<Card> drawCards(@PathVariable("username") String username) {
		return gameService.drawCardsFromDeck(username);
	}

	@PostMapping("/playCard/{row}/{index}")
	public void playCard(@PathVariable String row, @PathVariable int index) {
		gameService.playCard(index, row);
	}

	
	//vegul nem kellenek mert az angular oldja meg ezeket
	
//	@GetMapping("/updateRowSum/{row}")
//	public int getRowSum(@PathVariable String row) {
//		return boardService.updateRowSum(row);
//	}
//
//	@GetMapping("/udpateRowSumTotal")
//	public int getAllRowSum() {
//		return gameService.updateRowSumTotal();
//	}
//
//	@GetMapping("/melee-rows")
//	public List<Card> getMeleeRows() {
//		return boardService.rowsWithCards.get("melee");
//	}
//
//	@GetMapping("/ranged-rows")
//	public List<Card> getRangedRows() {
//		return boardService.rowsWithCards.get("ranged");
//	}
//
//	@GetMapping("/siege-rows")
//	public List<Card> getSiegeRows() {
//		return boardService.rowsWithCards.get("siege");
//	}
//	
//	@GetMapping("/clearRowsWithCards")
//	public void clearRowsWithCards() {
//	  boardService.clearAllCardsFromBoard();
//	}
}
