package com.springMongoDBLogin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springMongoDBLogin.domain.cardGame.Card;



//====================== ANGULAR CSINALJA EZEEKET A CARDHANDS.SPLICE-AL =======================
// DELETE?


@Service
public class BoardService {

	public Map<String, List<Card>> rowsWithCards;

	public BoardService() {
		rowsWithCards = new HashMap<>();
		rowsWithCards.put("melee", new ArrayList<>());
		rowsWithCards.put("ranged", new ArrayList<>());
		rowsWithCards.put("siege", new ArrayList<>());
	}

	// ezeket atadni frontendre, hogy ez udpatelje be mindig a nezetet az OnInit-ben
	public List<Card> meleeRowsToTheBoard(String row) {
		return rowsWithCards.get("melee");
	}

	public List<Card> rangedRowsToTheBoard(String row) {
		return rowsWithCards.get("ranged");
	}

	public List<Card> siegeRowsToTheBoard(String row) {
		return rowsWithCards.get("siege");
	}

	// ha mashova navigalnak az oldalon, az angular hivja meg ezt es torolje a
	// sorokban levo kartyakat
	public void clearAllCardsFromBoard() {
		rowsWithCards.put("melee", new ArrayList<>());
		rowsWithCards.put("ranged", new ArrayList<>());
		rowsWithCards.put("siege", new ArrayList<>());
	}

	// hozzaadni a megfelelo sorhoz
	public void addCardToTheRow(Card selectedCard, String row) {
		if (!rowsWithCards.containsKey(row)) {
			rowsWithCards.put(row, new ArrayList<Card>());
		}
		rowsWithCards.get(row).add(selectedCard);

		System.out.println(rowsWithCards.get(row));
		// updateRowSum(row);
		// updateTotalRowSum();
	}

	
	//AZ ANGULAR MAJD SZAMOLJA
//	// pontok szamolasa
//	public int updateRowSum(String row) {
//		int sum = 0;
//		for (Card card : rowsWithCards.get(row)) {
//			sum += card.getPower();
//		}
//		sumOfRow.put(row, sum);
//
//		System.out.println(sumOfRow.get("melee"));
//		System.out.println(sumOfRow.get("ranged"));
//		System.out.println(sumOfRow.get("siege"));
//		return sumOfRow.getOrDefault(row,0);
//	}
//
//	public int updateTotalRowSum() {
//		int totalSum = 0;
//		for (int sum : sumOfRow.values()) {
//			totalSum += sum;
//		}
//		return sumOfAllRows = totalSum;
//	}


}
