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

	public Card createCard(String name, String type, String encodedCard) throws IOException {
		Card card = new Card(name, type, encodedCard);
		return card;
	}

	public List<Card> CreateStartingDeckCards() throws IOException {

		ClassLoader classLoader = getClass().getClassLoader();

		InputStream aragornStream = classLoader.getResourceAsStream("images/aragornCard.png");
		InputStream boromirStream = classLoader.getResourceAsStream("images/bomoriCard.png");
		InputStream elendilStream = classLoader.getResourceAsStream("images/elendilCard.png");
		InputStream eomerStream = classLoader.getResourceAsStream("images/eomerCard.png");
		InputStream eowynStream = classLoader.getResourceAsStream("images/eowynCard.png");

		InputStream balinStream = classLoader.getResourceAsStream("images/balinCard.png");
		InputStream durinStream = classLoader.getResourceAsStream("images/durinCard.png");
		InputStream dwalinfGreyStream = classLoader.getResourceAsStream("images/dwalinCard.png");
		InputStream gimliTowerStream = classLoader.getResourceAsStream("images/gimliCard.png");
		InputStream thorinStream = classLoader.getResourceAsStream("images/thorinCard.png");

		InputStream elrondStream = classLoader.getResourceAsStream("images/elrondCard.png");
		InputStream feanorStream = classLoader.getResourceAsStream("images/feanorCard.png");
		InputStream galadrielStream = classLoader.getResourceAsStream("images/galadirelCard.png");
		InputStream gilgaladStream = classLoader.getResourceAsStream("images/gilgaladCard.png");
		InputStream legolasStream = classLoader.getResourceAsStream("images/legolasCard.png");

		InputStream bluewizardStream = classLoader.getResourceAsStream("images/bluwizardsCard.png");
		InputStream gandalfgreyStream = classLoader.getResourceAsStream("images/gandalfgreyCard.png");
		InputStream gandalfwhiteStream = classLoader.getResourceAsStream("images/gandalfwhiteCard.png");
		InputStream radagastStream = classLoader.getResourceAsStream("images/radagastCard.png");
		InputStream sarumanStream = classLoader.getResourceAsStream("images/sarumanCard.png");

		InputStream azogStream = classLoader.getResourceAsStream("images/azogCard.png");
		InputStream gothmogStream = classLoader.getResourceAsStream("images/gothmogCard.png");
		InputStream grishnakStream = classLoader.getResourceAsStream("images/grishnakCard.png");
		InputStream snagaStream = classLoader.getResourceAsStream("images/snagaCard.png");
		InputStream uglukStream = classLoader.getResourceAsStream("images/uglukCard.png");

		Card cardAragorn = createCard("Aragorn", "human", imageEncoder.encode(aragornStream.readAllBytes()));
		Card cardBoromir = createCard("Boromir", "human", imageEncoder.encode(boromirStream.readAllBytes()));
		Card cardElendil = createCard("Elendil", "human", imageEncoder.encode(elendilStream.readAllBytes()));
		Card cardEomer = createCard("Eomer", "human", imageEncoder.encode(eomerStream.readAllBytes()));
		Card cardEowyn = createCard("Eowyn", "human", imageEncoder.encode(eowynStream.readAllBytes()));

		Card cardBalin = createCard("Balin", "dwarf", imageEncoder.encode(balinStream.readAllBytes()));
		Card cardDurin = createCard("Durin", "dwarf", imageEncoder.encode(durinStream.readAllBytes()));
		Card cardDwalin = createCard("Dwalin", "dwarf", imageEncoder.encode(dwalinfGreyStream.readAllBytes()));
		Card cardGimli = createCard("Gimli", "dwarf", imageEncoder.encode(gimliTowerStream.readAllBytes()));
		Card cardThorin = createCard("Thorin", "dwarf", imageEncoder.encode(thorinStream.readAllBytes()));

		Card cardElrond = createCard("Elrond", "elf", imageEncoder.encode(elrondStream.readAllBytes()));
		Card cardFeanor = createCard("Feanor", "elf", imageEncoder.encode(feanorStream.readAllBytes()));
		Card cardGaladriel = createCard("Galadriel", "elf", imageEncoder.encode(galadrielStream.readAllBytes()));
		Card cardGilgalad = createCard("Gilgalad", "elf", imageEncoder.encode(gilgaladStream.readAllBytes()));
		Card cardLegolas = createCard("Legolas", "elf", imageEncoder.encode(legolasStream.readAllBytes()));

		Card cardBlueWizard = createCard("BlueWizard", "istari", imageEncoder.encode(bluewizardStream.readAllBytes()));
		Card cardGandalfGrey = createCard("GandalfGrey", "istari",
				imageEncoder.encode(gandalfgreyStream.readAllBytes()));
		Card cardGandalfWhite = createCard("GandalfWhite", "istari",
				imageEncoder.encode(gandalfwhiteStream.readAllBytes()));
		Card cardRadagast = createCard("Radagast", "istari", imageEncoder.encode(radagastStream.readAllBytes()));
		Card cardSaruman = createCard("Saruman", "istari", imageEncoder.encode(sarumanStream.readAllBytes()));

		Card cardAzog = createCard("Azog", "orc", imageEncoder.encode(azogStream.readAllBytes()));
		Card cardGothmog = createCard("Gothmog", "orc", imageEncoder.encode(gothmogStream.readAllBytes()));
		Card cardGrishnak = createCard("Grishnak", "orc", imageEncoder.encode(grishnakStream.readAllBytes()));
		Card cardSnaga = createCard("Snaga", "orc", imageEncoder.encode(snagaStream.readAllBytes()));
		Card cardUgluk = createCard("Ugluk", "orc", imageEncoder.encode(uglukStream.readAllBytes()));

		List<Card> startingDeck = Arrays.asList(cardAragorn, cardBoromir, cardElendil, cardEomer, cardEowyn, cardBalin,
				cardDurin, cardDwalin, cardGimli, cardThorin, cardElrond, cardFeanor, cardGaladriel, cardGilgalad,
				cardLegolas, cardBlueWizard, cardGandalfGrey, cardGandalfWhite, cardRadagast, cardSaruman, cardAzog,cardGothmog,cardGrishnak,cardSnaga,cardUgluk);

		return startingDeck;

	}

	// check uj kartyak a repo ellen es ha nincs benne akkor tegye bele a repoba
	public void addNewCardsToTheRepo() throws IOException {
		List<Card> startingDeck = CreateStartingDeckCards();
		for (Card card : startingDeck) {
			if (cardRepository.findByName(card.getName()) == null) {
				cardRepository.save(card);
				System.out.println(card.getName() + "saved to the repo");

			}
		}
	}

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

		Card newCard = new Card(card.getName(), card.getType(), card.getPicture());
		return cardRepository.save(newCard);
	}

}