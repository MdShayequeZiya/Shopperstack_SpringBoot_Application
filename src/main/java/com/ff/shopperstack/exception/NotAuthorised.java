package com.ff.shopperstack.exception;

public class NotAuthorised extends RuntimeException {
	
	
	String message = "You are not authorized to perform this operation.";
	
	@Override
	public String getMessage() {
		return message;
	}

	public NotAuthorised(String message) {
		super();
		this.message = message;
	}

	public NotAuthorised() {
		super();
	}
	
	

}
