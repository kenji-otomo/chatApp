package com.example.chatApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.chatApp.domain.Chat;

@Mapper
public interface ChatRepository {

	public void createChat(Chat chat);
	
	public List<Chat> searchChatByRoomId(Chat chat);
}
