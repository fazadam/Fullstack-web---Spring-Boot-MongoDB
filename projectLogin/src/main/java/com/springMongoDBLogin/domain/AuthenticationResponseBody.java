package com.springMongoDBLogin.domain;

public class AuthenticationResponseBody {

	public final Boolean authenticated;
	
	public AuthenticationResponseBody(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}
}
