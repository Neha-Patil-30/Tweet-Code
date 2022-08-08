package com.tweetapp.api.exception;

public class PreExsistingUsername extends Exception{
	
	/**
	 * UsernameAlreadyExists Exception
	 */
	private static final long serialVersionUID = 1L;

	public PreExsistingUsername(String message) {

		super(message);
	}
}
