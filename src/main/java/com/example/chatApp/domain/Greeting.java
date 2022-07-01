package com.example.chatApp.domain;

public class Greeting {

	private String content;

	public Greeting() {
	}

	public String getContent() {
		return content;
	}

	public Greeting(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Greeting [content=" + content + "]";
	}
}
