package com.tweetapp.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tweetapp.api.security.model.AuthenticationResponse;

@Component
public class Validationservice {

	@Autowired
	private JwtUtil jwtutil;
	private AuthenticationResponse authenticationResponse;

	/**
	 * The Validationservice class for validating token
	 *
	 */
	public AuthenticationResponse validate(String token) {
		authenticationResponse = new AuthenticationResponse();
		String jwt = token.substring(7);

		if (jwtutil.validateToken(jwt)) {
			authenticationResponse.setUsername((jwtutil.extractUsername(jwt)));;
			authenticationResponse.setValid(true);
		} else {
			authenticationResponse.setValid(false);
		}
		return authenticationResponse;
	}
}