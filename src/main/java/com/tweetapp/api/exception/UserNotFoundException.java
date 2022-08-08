package com.tweetapp.api.exception;
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * The UserNotFoundException class for Exception when user is not found
	 *
	 */
	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}
}
