package com.customException;

public class AccountNotFoundException extends Exception{

	public AccountNotFoundException(String message){
		super(message);
	}
	
}
