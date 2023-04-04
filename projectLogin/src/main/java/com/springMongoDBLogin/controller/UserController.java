package com.springMongoDBLogin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springMongoDBLogin.domain.Role;
import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.domain.cardGame.Card;
import com.springMongoDBLogin.service.CardService;
import com.springMongoDBLogin.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserDetailsServiceImpl userService;
	@Autowired
	private CardService cardService;

//	@RequestMapping("/")
//	public String home() {
//		return "home";
//	}

	@PostMapping("/registration")
	public ResponseEntity<String> registration(@RequestBody User user) throws IOException {
		try {
			userService.registerUser(user);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/cards/{username}")
	public List<Card> getCardsByUser(@PathVariable("username") String username) {
		User user = (User) userService.loadUserByUsername(username);
		System.out.println("a kartya lista at van kuldve");
		return user.getAllCards();
	}

	@GetMapping("/roles/{username}")
	public List<String> getUserRoleNames(@PathVariable("username") String username) {
		User user = (User) userService.loadUserByUsername(username);
		Set<Role> userRoles = user.getRoles();
		List<String> roleNames = new ArrayList<>();
		for (Role role : userRoles) {
			roleNames.add(role.getName());
		}
		return roleNames;
	}

	@PostMapping("/profile/{username}/adminRequest")
	public ResponseEntity<String> setPendingAdminRequest(@PathVariable String username) {

		try {
			userService.setPendingAdminRequest(username);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/profile/grandAdminRequest/{username}")
	public ResponseEntity<String> grantPendingAdminRequest(@PathVariable String username) {

		try {

			userService.grantPendingAdminRequest(username);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/profile/declineAdminRequest/{username}")
	public ResponseEntity<String> declineAdminRequest(@PathVariable String username) {

		try {

			userService.declinePendingAdminRequest(username);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/profile/revokeAdminRole/{username}")
	public ResponseEntity<String> revokeAdminRole(@PathVariable String username) {

		try {

			userService.revokeAdminRole(username);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/profile/deleteUser/{username}")
	public ResponseEntity<String> deletUser(@PathVariable String username) {

		try {

			userService.deleteUserFromRepo(username);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/profile/{username}")
	public UserDetails getUser(@PathVariable("username") String username) {
		User user = (User) userService.loadUserByUsername(username);
		return user;
	}

	// Set favorite videos
	@PutMapping("/{username}/setfavoriteVideos")
	public ResponseEntity<String> setUserFavoriteVideos(@PathVariable String username,
			@RequestBody String favoriteVideosUrl) {
		try {
			userService.setFavouriteVideos(username, favoriteVideosUrl);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Get favorite videos
	@GetMapping("/{username}/getfavoriteVideos")
	public Set<String> getUserFavoriteVideos(@PathVariable String username) {
		return userService.getFavouriteVideos(username);
	}

	// Delete favorite videos
	@PutMapping("/{username}/deletefavoriteVideos")
	public void deleteUserFavoriteVideos(@PathVariable String username, @RequestBody String favoriteVideosUrl) {
		userService.removeFavoriteVideos(username, favoriteVideosUrl);
	}

//	@PutMapping("/profile/{username}/updateProfile")
//	public ResponseEntity<User> updateUserData(@PathVariable("username") String username,@RequestBody User userToUpdate) {
//		User udpatedUser = userService.updateUserData(username, userToUpdate);
//		System.out.println("user updated" + username + " with new values" + userToUpdate);
//		return ResponseEntity.ok().body(udpatedUser);
//	}

	@PutMapping("/profile/{username}/updateProfileUsername")
	public ResponseEntity<String> updateUserUsername(@PathVariable("username") String username,
			@RequestBody String newUsername) {
		try {
			userService.updateProfileUsername(username, newUsername);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/profile/{username}/updateProfilePassword")
	public ResponseEntity<String> updateUserPassword(@PathVariable("username") String username,
			@RequestBody String newPassword) {
		try {
			userService.updateProfilePassword(username, newPassword);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}

	@PutMapping("/profile/{username}/updateProfileEmail")
	public ResponseEntity<String> updateUserEmail(@PathVariable("username") String username,
			@RequestBody String newEmail) {
		try {
			userService.updateProfileEmail(username, newEmail);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// ==========ADMIN page apis============

	@GetMapping("/adminPage")
	public List<User> listAllUsers() {
		return userService.listAllUsers();
	}

	@GetMapping("/allCards")
	public List<Card> allCardsList() {
		return userService.allCardsList();
	}

	@GetMapping("/pendingRequestsList")
	public List<User> listAllPendingRequests() {
		System.out.println(userService.listAllPendingAdminRequestUsers());
		return userService.listAllPendingAdminRequestUsers();
	}

	@PutMapping("/grantAdminRole/{username}")
	public ResponseEntity<String> grantAdminRole(@PathVariable("username") String username) {

		try {
			userService.grantPendingAdminRequest(username);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/deleteCard/{cardName}")
	public ResponseEntity<String> deleteCardFromRepo(@PathVariable("cardName") String cardName) {

		try {
			cardService.deleteCardFromRepo(cardName);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/createNewCardFrontend")
	public ResponseEntity<String> createNewCardFromFrontend(@RequestBody Card card) throws IOException {
		try {
			cardService.createNewCardFromFrontend(card);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	// ======= deck reszek =====

	@PutMapping("/{username}/cards/{deckname}")
	public ResponseEntity<String> createDeck(@PathVariable String username, @PathVariable String deckname,
			@RequestBody List<String> cardList) {
		try {
			userService.createDeck(username, deckname, cardList);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{username}/cards/{deckname}/deckCards")
	public List<Card> giveUsersDeckCards(@PathVariable String username, @PathVariable String deckname) {
		return userService.getCardsForDeck(username, deckname);
	}

	@GetMapping("/{username}/cards/{deckname}/existingDecks")
	public List<Card> giveUsersExistingDecks(@PathVariable String username, @PathVariable String deckname) {
		return userService.getCardsForDeck(username, deckname);
	}

	@PutMapping("/{username}/cards/{deckname}/deleteDeck")
	public ResponseEntity<String> deleteUsersDeck(@PathVariable("username") String username,
			@PathVariable("deckname") String deckname) {
		try {
			userService.deleteUsersDeck(username, deckname);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
