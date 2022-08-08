package com.tweetapp.api.security.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tweetapp.api.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

	// Class to Implement UserDetailsService in Spring security

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<com.tweetapp.api.model.User> user;
		user = userRepository.findByUsernameContains(username);
		if (user.isPresent()) {
			com.tweetapp.api.model.User u = user.get();
			return new User(u.getUsername(), u.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Username/Password is Invalid...Please Check");
		}
	}

}
