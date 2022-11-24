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
		
		Chat chat = new Chat(message.getUserId(), message.getMessage(), message.getRoomId());

		if (message.getImage() != null) {
			String fileName = chatService.saveImg(message);
			chat.setPicturePath(fileName);
		}

		chatService.createChat(chat);

		String content = HtmlUtils.htmlEscape(message.getName())
				+ " : "
				+ HtmlUtils.htmlEscape(message.getMessage());
		
		chat.setContent(content);
		return chat;
	}
	
}