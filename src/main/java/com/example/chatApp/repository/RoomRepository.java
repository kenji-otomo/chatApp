package com.example.chatApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.chatApp.domain.Room;
import com.example.chatApp.domain.User;

@Mapper
public interface RoomRepository {
	
	public Room searchRoomByRoomId(Room room);
	
	public void createRoom(Room room);
	
	public List<Room> searchRoomByUserId(User user);
}
