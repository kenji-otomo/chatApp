package com.example.chatApp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.example.chatApp.domain.Greeting;
import com.example.chatApp.domain.HelloMessage;

@RestController
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		System.out.println("message : "+message);
		Thread.sleep(1000);
		return new Greeting(HtmlUtils.htmlEscape(message.getName())
				+ " : "
				+ HtmlUtils.htmlEscape(message.getMessage())
				);
	}
	
}
