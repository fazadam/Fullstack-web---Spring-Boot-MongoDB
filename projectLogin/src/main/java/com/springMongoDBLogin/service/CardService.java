package com.springMongoDBLogin.service;

import java.io.IOException;
import java.io.InputStream;
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
		
		
//		Card cardAragorn = createCard("Aragorn", 15, "melee", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\aragornGwent.png")));
//		Card cardLegolas = createCard("Legolas", 15, "ranged", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\legolasCard.png")));
//		Card cardGondorianArcher = createCard("Gondorian Archer", 5, "ranged", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\gondorArcherCard.png")));
//		Card cardRohirrimRider = createCard("Rohirrim Rider", 7, "melee", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\rohirrimRiderCard.png")));
//		Card cardGimli = createCard("Gimli", 15, "melee", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\gimliCard.png")));
//		Card cardFrodo = createCard("Frodo", 8, "melee", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\frodoCard.png")));
//		Card cardElrond = createCard("Elrond", 20, "melee", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\elrondCard.png")));
//		Card cardGandalfGrey = createCard("GandalfGrey", 10, "ranged", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\gandalfgreyCard.png")));
//		Card orcSiegeTowerCard = createCard("Orc Siege Tower", 8, "siege", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\orcSiegeTowerCard.png")));
//		Card balrogCard = createCard("Balrog", 25, "melee", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\balrogCard.png")));
//		Card grondCard = createCard("Grond", 17, "siege", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\grondCard.png")));
//		Card orcFighterCard = createCard("Orc Fighter", 3, "melee", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\orcFighterCard.png")));
//		Card orcCommanderCard = createCard("Orc Commander", 8, "melee", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\orcCommanderCard.png")));
//		Card giantEaglesCard = createCard("Giant Eagles", 15, "ranged", imageEncoder.encode(Paths.get(
//				"C:\\Users\\fazad\\eclipse-workspace\\projectLogin\\src\\main\\resources\\images\\giantEaglesCard.png")));

		ClassLoader classLoader = getClass().getClassLoader();
		
		InputStream aragornStream = classLoader.getResourceAsStream("images/aragornGwent.png");
		InputStream legolasStream = classLoader.getResourceAsStream("images/legolasCard.png");
		InputStream gondorArcherStream = classLoader.getResourceAsStream("images/gondorArcherCard.png");
		InputStream rohirrimRiderStream = classLoader.getResourceAsStream("images/rohirrimRiderCard.png");
		InputStream gimliStream = classLoader.getResourceAsStream("images/gimliCard.png");
		InputStream frodoStream = classLoader.getResourceAsStream("images/frodoCard.png");
		InputStream elrondStream = classLoader.getResourceAsStream("images/elrondCard.png");
		InputStream gandalfGreyStream = classLoader.getResourceAsStream("images/gandalfgreyCard.png");
		InputStream orcSiegeTowerStream = classLoader.getResourceAsStream("images/orcSiegeTowerCard.png");
		InputStream balrogStream = classLoader.getResourceAsStream("images/balrogCard.png");
		InputStream grondStream = classLoader.getResourceAsStream("images/grondCard.png");
		InputStream orcFighterStream = classLoader.getResourceAsStream("images/orcFighterCard.png");
		InputStream orcCommanderStream = classLoader.getResourceAsStream("images/orcCommanderCard.png");
		InputStream giantEaglesStream = classLoader.getResourceAsStream("images/giantEaglesCard.png");
		InputStream sarumanStream = classLoader.getResourceAsStream("images/sarumanCard.png");

		Card cardAragorn = createCard("Aragorn", 15, "melee", imageEncoder.encode(aragornStream.readAllBytes()));
		Card cardLegolas = createCard("Legolas", 15, "ranged", imageEncoder.encode(legolasStream.readAllBytes()));
		Card cardGondorianArcher = createCard("Gondorian Archer", 5, "ranged", imageEncoder.encode(gondorArcherStream.readAllBytes()));
		Card cardRohirrimRider = createCard("Rohirrim Rider", 7, "melee", imageEncoder.encode(rohirrimRiderStream.readAllBytes()));
		Card cardGimli = createCard("Gimli", 15, "melee", imageEncoder.encode(gimliStream.readAllBytes()));
		Card cardFrodo = createCard("Frodo", 10, "melee", imageEncoder.encode(frodoStream.readAllBytes()));
		Card cardElrond = createCard("Elrond", 20, "melee", imageEncoder.encode(elrondStream.readAllBytes()));
		Card cardGandalfGrey = createCard("GandalfGrey", 10, "ranged", imageEncoder.encode(gandalfGreyStream.readAllBytes()));
		Card orcSiegeTowerCard = createCard("Orc Siege Tower", 8, "siege", imageEncoder.encode(orcSiegeTowerStream.readAllBytes()));
		Card balrogCard = createCard("Balrog", 25, "melee", imageEncoder.encode(balrogStream.readAllBytes()));
		Card grondCard = createCard("Grond", 17, "siege", imageEncoder.encode(grondStream.readAllBytes()));
		Card orcFighterCard = createCard("Orc Fighter", 3, "melee", imageEncoder.encode(orcFighterStream.readAllBytes()));
		Card orcCommanderCard = createCard("Orc Commander", 8, "melee", imageEncoder.encode(orcCommanderStream.readAllBytes()));
		Card giantEaglesCard = createCard("Giant Eagles", 15, "ranged", imageEncoder.encode(giantEaglesStream.readAllBytes()));
		Card sarumanCard = createCard("Saruman", 15, "ranged", imageEncoder.encode(sarumanStream.readAllBytes()));

		List<Card> startingDeck = Arrays.asList(cardAragorn, cardLegolas, cardGondorianArcher, cardRohirrimRider,
				cardGimli, cardFrodo, cardElrond, cardGandalfGrey, orcSiegeTowerCard, balrogCard, grondCard,
				orcFighterCard, orcCommanderCard, giantEaglesCard,sarumanCard);

		return startingDeck;

	}

//	// check uj kartyak a repo ellen es ha nincs benne akkor tegye bele a repoba
//	public void addNewCardsToTheRepo() throws IOException {
//		List<Card> startingDeck = CreateStartingDeckCards();
//		for (Card card : startingDeck) {
//			if (cardRepository.findByName(card.getName()) == null) {
//				cardRepository.save(card);
//				System.out.println(card.getName() + "saved to the repo");
//				
//			}
//		}
//	}

	public List<Card> getAllCardsFromTheRepo() {
		return cardRepository.findAll();
	}
	
	public void deleteCardFromRepo(String cardName) {
		Card cardToDelete = cardRepository.findByName(cardName);
		cardRepository.delete(cardToDelete);
	}
	
	public Card createNewCardFromFrontend(Card card) throws IOException {
	    if (cardRepository.findByName(card.getName()) != null) {
	        throw new RuntimeException("Card with name " + card.getName() + " already exists");
	    }

	    
	    Card newCard = new Card(card.getName(), card.getPower(), card.getType(), card.getPicture());
	    return cardRepository.save(newCard);
	}
	}
