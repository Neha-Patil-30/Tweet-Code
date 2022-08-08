package com.tweetapp.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.tweetapp.api.exception.UserNotFoundException;
import com.tweetapp.api.model.User;
import com.tweetapp.api.security.model.TokenResponse;

@Component

public class LoginService {

	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private CustomerDetailsService customerDetailservice;

	/**
	 * The LoginService class for request login
	 *
	 */
	public TokenResponse userLogin(User appuser) throws UserNotFoundException {

		UserDetails userdetails = customerDetailservice.loadUserByUsername(appuser.getUsername());

		if (encoder.matches(appuser.getPassword(), userdetails.getPassword()) && (userdetails != null)) {
			String username = appuser.getUsername();
			String token = jwtutil.generateToken(userdetails);
			return new TokenResponse(username, token);
		} else {
			throw new UserNotFoundException("Username/Password is Invalid...Please Check");
		}
	}

}