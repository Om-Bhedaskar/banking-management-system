package com.customException;

public class CustomerNotFoundException extends Exception{

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
