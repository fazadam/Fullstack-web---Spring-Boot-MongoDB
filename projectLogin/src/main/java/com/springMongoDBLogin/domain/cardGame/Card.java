package com.springMongoDBLogin.domain.cardGame;

import java.io.IOException;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Card {

	@Id
	
	private String id;
	
	private String name;
		
	private String type;
	
	private String picture;
	
	public Card( String name, String type,String picture) throws IOException{
		super();
		this.name = name;
		this.type = type;
		this.picture = picture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
