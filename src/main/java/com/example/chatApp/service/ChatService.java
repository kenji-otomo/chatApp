package com.example.chatApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chatApp.domain.Chat;
import com.example.chatApp.repository.ChatRepository;

@Service
@Transactional
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	public void createChat(Chat chat) {
		chatRepository.createChat(chat);
	}
	
	public List<Chat> searchChatByRoomId(Chat chat) {
		
		return chatRepository.searchChatByRoomId(chat);
	}
}
