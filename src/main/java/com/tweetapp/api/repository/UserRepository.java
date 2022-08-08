package com.tweetapp.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.api.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	List<User> findByUsernameContaining(String username);
	User findByUsername(String username);
	Optional<User> findByUsernameContains(String username);
	

}
