package com.example.chatApp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.example.chatApp.domain.Greeting;
import com.example.chatApp.domain.Message;

@RestController
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topics/greetings")
	public Greeting sendChat(Message message) {
		System.out.println(message);
		return new Greeting(HtmlUtils.htmlEscape(message.getName())
				+ " : "
				+ HtmlUtils.htmlEscape(message.getMessage()));
	}
}
