package com.springMongoDBLogin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springMongoDBLogin.domain.Role;
import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.domain.cardGame.Card;

@Service
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String email;

	private String password;
	
	private List<Card> allCards = new ArrayList<>();

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl() {
	}

	public UserDetailsImpl(String id, String username, String email, String password, List<Card> allCards,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.allCards = allCards;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getAllCards(),authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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

	public List<Card> getAllCards() {
		return allCards;
	}

	public void setAllCards(List<Card> allCards) {
		this.allCards = allCards;
	}

}
