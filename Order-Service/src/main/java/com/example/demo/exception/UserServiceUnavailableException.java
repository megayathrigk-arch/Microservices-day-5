package com.example.demo.exception;

public class UserServiceUnavailableException extends RuntimeException{
	
	public UserServiceUnavailableException(String message) {
		super(message);
	}

}