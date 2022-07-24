package com.example.chatApp.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Room {

	private String roomId;
	private String roomPass;
	private String roomName;
	private boolean privateRoom;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
	private boolean deleted;
	private List<User> roomUserList;
	
	public Room() {
		super();
	}

	public Room(String roomId) {
		super();
		this.roomId = roomId;
	}
	
	public Room(String roomId, String roomName) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
	}

	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomPass() {
		return roomPass;
	}
	public void setRoomPass(String roomPass) {
		this.roomPass = roomPass;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public boolean getPrivateRoom() {
		return privateRoom;
	}
	public void setPrivateRoom(Boolean privateRoom) {
		this.privateRoom = privateRoom;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public List<User> getRoomUserList() {
		return roomUserList;
	}
	public void setRoomUserList(List<User> roomUserList) {
		this.roomUserList = roomUserList;
	}
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomPass=" + roomPass + ", roomName=" + roomName + ", privateRoom="
				+ privateRoom + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", deleted="
				+ deleted + ", roomUserList=" + roomUserList + "]";
	}
}
