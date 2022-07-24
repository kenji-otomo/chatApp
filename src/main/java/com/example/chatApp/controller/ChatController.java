package com.example.chatApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.example.chatApp.domain.Chat;
import com.example.chatApp.form.Message;
import com.example.chatApp.service.ChatService;

@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;

	@MessageMapping("/chat/{roomId}")
	@SendTo("/topics/greetings/{roomId}")
	public Chat sendChat(Message message) {
//		System.out.println(message);
//		System.out.println("roomId:"+roomId);
//		System.out.println("roomId:"+message.getRoomId());
//		System.out.println("userId:"+message.getUserId());
		
		Chat chat = new Chat(message.getUserId(),message.getMessage(),message.getRoomId());
//		System.out.println(chat);
		chatService.createChat(chat);
		
		chat.setContent(HtmlUtils.htmlEscape(message.getName())
				+ " : "
				+ HtmlUtils.htmlEscape(message.getMessage()));
		
		return chat;
	}
	
}
