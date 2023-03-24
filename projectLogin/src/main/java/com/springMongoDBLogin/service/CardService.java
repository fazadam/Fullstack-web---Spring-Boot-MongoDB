package com.springMongoDBLogin.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springMongoDBLogin.domain.cardGame.Card;
import com.springMongoDBLogin.repository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private imageEncoder imageEncoder;

	public Card createCard(String name, int power, String type, String encodedCard) throws IOException {
		Card card = new Card(name, power, type, encodedCard);
		return card;
	}

	public List<Card> CreateStartingDeckCards() throws IOException {
		Card cardAragorn = createCard("Aragorn", 15, "melee", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\aragornGwent.png")));
		Card cardLegolas = createCard("Legolas", 15, "ranged", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\legolasCard.png")));
		Card cardGondorianArcher = createCard("Gondorian Archer", 5, "ranged", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\gondorArcherCard.png")));
		Card cardRohirrimRider = createCard("Rohirrim Rider", 7, "melee", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\rohirrimRiderCard.png")));
		Card cardGimli = createCard("Gimli", 15, "melee", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\gimliCard.png")));
		Card cardFrodo = createCard("Frodo", 8, "melee", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\frodoCard.png")));
		Card cardElrond = createCard("Elrond", 20, "melee", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\elrondCard.png")));
		Card cardGandalfGrey = createCard("GandalfGrey", 10, "ranged", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\gandalfgreyCard.png")));
		Card orcSiegeTowerCard = createCard("Orc Siege Tower", 8, "siege", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\orcSiegeTowerCard.png")));
		Card balrogCard = createCard("Balrog", 25, "melee", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\balrogCard.png")));
		Card grondCard = createCard("Grond", 17, "siege", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\grondCard.png")));
		Card orcFighterCard = createCard("Orc Fighter", 3, "melee", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\orcFighterCard.png")));
		Card orcCommanderCard = createCard("Orc Commander", 8, "melee", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\orcCommanderCard.png")));
		Card giantEaglesCard = createCard("Giant Eagles", 15, "ranged", imageEncoder.encode(Paths.get(
				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\giantEaglesCard.png")));

		List<Card> startingDeck = Arrays.asList(cardAragorn, cardLegolas, cardGondorianArcher, cardRohirrimRider,
				cardGimli, cardFrodo, cardElrond,cardGandalfGrey,orcSiegeTowerCard,balrogCard,grondCard,orcFighterCard,orcCommanderCard,giantEaglesCard);

		return startingDeck;

	}
	
	//check uj kartyak a repo ellen es ha nincs benne akkor tegye bele a repoba
	public void addNewCardsToTheRepo() throws IOException {
		List<Card> startingDeck = CreateStartingDeckCards();
		for(Card card :startingDeck) {
			if(cardRepository.findByName(card.getName())==null) {
				cardRepository.save(card);
			}
		}
	}
	
	public List<Card> getAllCardsFromTheRepo(){
		return cardRepository.findAll();
		}
}