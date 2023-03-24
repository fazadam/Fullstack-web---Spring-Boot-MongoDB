package com.springMongoDBLogin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.domain.cardGame.Card;

import com.springMongoDBLogin.service.UserDetailsImpl;
import com.springMongoDBLogin.service.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserDetailsServiceImpl userService;

//	@RequestMapping("/")
//	public String home() {
//		return "home";
//	}

	@PostMapping("/registration")
	public ResponseEntity<?> registration(@RequestBody User user) throws IOException {
		userService.registerUser(user);
		return ResponseEntity.ok().body("user registered successfully");
	}

	@GetMapping("/cards/{username}")
	public List<Card> getCardsByUserId(@PathVariable("username") String username) {
        UserDetailsImpl user =  (UserDetailsImpl) userService.loadUserByUsername(username);
        System.out.println("a kartya lista at van kuldve");        
        return user.getAllCards();
    }

	@GetMapping("/profile/{username}")
	public  UserDetails getUser(@PathVariable("username") String username) {
        UserDetailsImpl user =  (UserDetailsImpl) userService.loadUserByUsername(username);
        return user;
	}
	
	@PutMapping("/profile/{username}/updateProfile")
	public ResponseEntity<User> updateUserData(@PathVariable("username") String username,@RequestBody User userToUpdate) {
		User udpatedUser = userService.updateUserData(username, userToUpdate);
		System.out.println("user updated" + username + " with new values" + userToUpdate);
		return ResponseEntity.ok().body(udpatedUser);
	}

	@PutMapping("/profile/{username}/updateProfileUsername")
	public ResponseEntity<Void> updateUserUsername(@PathVariable("username") String username,@RequestBody String newUsername) {
		userService.updateProfileUsername(username, newUsername);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/profile/{username}/updateProfilePassword")
	public ResponseEntity<Void> updateUserPassword(@PathVariable("username") String username,@RequestBody String newPassword) {
		userService.updateProfilePassword(username, newPassword);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/profile/{username}/updateProfileEmail")
	public ResponseEntity<Void> updateUserEmail(@PathVariable("username") String username,@RequestBody String newEmail) {
		userService.updateProfileEmail(username, newEmail);
		return ResponseEntity.noContent().build();
	}
	
//
//	@GetMapping("/cards")
//	public List<Card> getCrads(){
//		return cardService.getAllCardsFromTheRepo();
//	}
}
