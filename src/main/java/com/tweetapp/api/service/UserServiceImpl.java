package com.tweetapp.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.api.exception.PreExsistingUsername;
import com.tweetapp.api.exception.UnregisteredUsernameOrPassword;
import com.tweetapp.api.kafka.ProducerService;
import com.tweetapp.api.model.User;
import com.tweetapp.api.model.UserResponse;
import com.tweetapp.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	String Status = "success";

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProducerService producerService;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public User createUser(User user) throws PreExsistingUsername {
		if (userRepository.findByUsername(user.getFirstName()) != null)
			throw new PreExsistingUsername("Username already exists");
		logger.info("Registration successfully...");
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public int deleteUser(User user) {
		userRepository.delete(user);
		logger.info("Deleted user successfully..");
		return 1;
	}

	@Override
	public List<User> getAllUsers() {
		logger.info("Retriving all the users");
		return userRepository.findAll();
	}

	@Override
	public List<User> getUserByUsername(String username) throws UnregisteredUsernameOrPassword {
		if (userRepository.findByUsernameContaining(username) == null)
			throw new UnregisteredUsernameOrPassword("Please enter a valid username");
		logger.info("Retriving the user by the username.." + username);
		return userRepository.findByUsernameContaining(username);
	}

	@Override
	public Optional<User> getUserById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public UserResponse loginUser(String username, String password) throws UnregisteredUsernameOrPassword {

		UserResponse response = new UserResponse();
		try {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					response.setUser(user);
					response.setLoginStatus("success");
					logger.info("Login Successful..");
				} else
					throw new UnregisteredUsernameOrPassword("Unregistered Username Or Password Exception");
			} else
				throw new UnregisteredUsernameOrPassword("Unregistered Username Or Password Exception");
		} catch (UnregisteredUsernameOrPassword unregisteredUsernameOrPasswordException) {
			response.setLoginStatus("failed");
			logger.info("Login failed");
			throw new UnregisteredUsernameOrPassword("Unregistered Username Or Password Exception");
		}
		return response;
	}

	@Override
	public Map<String, String> forgotPassword(String username) {
		Map<String, String> map = new HashMap<String, String>();
		User user = userRepository.findByUsername(username);
		String newPassword=UUID.randomUUID().toString();
		user.setPassword(encoder.encode(newPassword));
		//encoder.encode(user.getPassword()
		userRepository.save(user);
		map.put("newPassword", newPassword);
		map.put("resetStatus", Status);
		logger.info("Completed..");
		return map;
	}

	@Override
	public Map<String, String> resetPassword(String username, String password) {
		Map<String, String> map = new HashMap<String, String>();
		User user = userRepository.findByUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		map.put("newPassword", user.getPassword());
		map.put("resetStatus", Status);
		logger.info("Reset password successful..");
		return map;
	}

}
