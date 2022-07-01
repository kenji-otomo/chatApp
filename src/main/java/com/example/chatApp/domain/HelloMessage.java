package com.example.chatApp.domain;

public class HelloMessage {

	private String name;
	private String message;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Message [name=" + name + ", message=" + message + "]";
	}
	
	public HelloMessage() {
		super();
	}
	
	public HelloMessage(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}
}
