package com.springMongoDBLogin.controller;

import java.io.IOException;
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

import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.domain.cardGame.Card;

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
	public ResponseEntity<String> registration(@RequestBody User user) throws IOException {
		try{
			userService.registerUser(user);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/cards/{username}")
	public List<Card> getCardsByUserId(@PathVariable("username") String username) {
        User user =   (User) userService.loadUserByUsername(username);
        System.out.println("a kartya lista at van kuldve");        
        return user.getAllCards();
    }

	@GetMapping("/profile/{username}")
	public  UserDetails getUser(@PathVariable("username") String username) {
		User user =  (User) userService.loadUserByUsername(username);
        return user;
	}
	
	// Set  favorite videos
	@PutMapping("/{username}/setfavoriteVideos")
	public ResponseEntity<String> setUserFavoriteVideos(@PathVariable String username, @RequestBody String favoriteVideosUrl) {
	    try {
		userService.setFavouriteVideos(username, favoriteVideosUrl);
		return ResponseEntity.ok().build();

	    }catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	// Get  favorite videos
	@GetMapping("/{username}/getfavoriteVideos")
	public Set<String> getUserFavoriteVideos(@PathVariable String username) {
	    return userService.getFavouriteVideos(username);
	}
	
	// Delete  favorite videos
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
	public ResponseEntity<String> updateUserUsername(@PathVariable("username") String username,@RequestBody String newUsername) {
		try {
		userService.updateProfileUsername(username, newUsername);
		return ResponseEntity.noContent().build();
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/profile/{username}/updateProfilePassword")
	public ResponseEntity<String> updateUserPassword(@PathVariable("username") String username,@RequestBody String newPassword) {
		try {
		userService.updateProfilePassword(username, newPassword);
		return ResponseEntity.noContent().build();
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	@PutMapping("/profile/{username}/updateProfileEmail")
	public ResponseEntity<String> updateUserEmail(@PathVariable("username") String username,@RequestBody String newEmail) {
		try {
		userService.updateProfileEmail(username, newEmail);
		return ResponseEntity.noContent().build();
		} catch(Exception e){
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

}
