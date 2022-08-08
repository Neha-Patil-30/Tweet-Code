package com.tweetapp.api.security.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private HttpStatus code;
	private String message;
	private String reason;

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ErrorResponse(HttpStatus code, String message, String reason) {
		super();
		this.code = code;
		this.message = message;
		this.reason = reason;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ErrorResponse [code=" + code + ", message=" + message + ", reason=" + reason + "]";
	}
}