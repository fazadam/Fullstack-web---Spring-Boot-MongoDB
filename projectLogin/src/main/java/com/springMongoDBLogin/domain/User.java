package com.springMongoDBLogin.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.springMongoDBLogin.domain.cardGame.Card;

@Document
public class User {

	// mongoDB automatikusan ad hozza generated ID-t, de itt tesztelem
	
	@Id
	private String id;

	private String email;
	private String username;
	private String password;

	private Set<Role> roles;
	
	private List<Card> allCards = new ArrayList<>();

	public User() {
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	//teszt regisztralas
	public User(String username, String password, String email, List<Card> allCards) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.allCards = allCards;
	}

	//nincsenek kartyak teszt
	public User(String id, String email, String username, String password, Set<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	//minden van
	public User(String id, String email, String username, String password, Set<Role> roles, List<Card> allCards) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.allCards = allCards;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Card> getAllCards() {
		return allCards;
	}

	public void setAllCards(List<Card> allCards) {
		this.allCards = allCards;
	}
	
	

}
