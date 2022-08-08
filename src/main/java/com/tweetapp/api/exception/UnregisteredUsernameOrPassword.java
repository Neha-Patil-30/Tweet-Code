package com.tweetapp.api.exception;

public class UnregisteredUsernameOrPassword extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnregisteredUsernameOrPassword(String message){
		super(message);
	}
	
}
