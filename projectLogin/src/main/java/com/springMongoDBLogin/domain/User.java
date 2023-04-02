package com.springMongoDBLogin.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springMongoDBLogin.domain.cardGame.Card;

@Document
public class User implements UserDetails {

	// mongoDB automatikusan ad hozza generated ID-t, de itt tesztelem
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String email;
	
	private String username;
	
	private String password;
	
	private String rawPassword;
	
	private Set<String> favouriteVideos;

	private Set<Role> roles;
	
	private Set<Role> pendingRoleRequests;
	
	private List<Card> allCards = new ArrayList<>();
	
	private Collection<? extends GrantedAuthority> authorities;


	public User() {
	}


	public User(String id, String username, String email, String password, List<Card> allCards,
			Collection<? extends GrantedAuthority> authorities,Set<String> favouriteVideos,Set<Role> pendingRoleRequests) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.allCards = allCards;
		this.authorities = authorities;
		this.favouriteVideos = favouriteVideos;
		this.pendingRoleRequests = pendingRoleRequests;
	}
	
	
	public static User build(User user) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getAllCards(),authorities,user.getFavouriteVideos(),user.getPendingRoleRequests());
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;

	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public Set<String> getFavouriteVideos() {
		return favouriteVideos;
	}

	public void setFavouriteVideos(Set<String> favouriteVideos) {
		this.favouriteVideos = favouriteVideos;
	}


	public Set<Role> getPendingRoleRequests() {
		return pendingRoleRequests;
	}


	public void setPendingRoleRequests(Set<Role> pendingRoleRequests) {
		this.pendingRoleRequests = pendingRoleRequests;
	}
	

}
