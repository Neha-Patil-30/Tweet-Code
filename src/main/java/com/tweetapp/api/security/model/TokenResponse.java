package com.tweetapp.api.security.model;

public class TokenResponse {
	private String username;
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenResponse() {
		super();
		
	}

	public TokenResponse(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	@Override
	public String toString() {
		return "TokenResponse [username=" + username + ", token=" + token + "]";
	}


}
