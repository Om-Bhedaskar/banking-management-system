package com.customException;

public class IdAlreadyExists extends Exception{
	
	public IdAlreadyExists(String message){
		super(message);
	}
}
