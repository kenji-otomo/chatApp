package com.example.chatApp.form;

public class Message {

	private String name;
	private String message;
	private String roomId;
	private Integer userId;
	
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
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Message [name=" + name + ", message=" + message + ", roomId=" + roomId + ", userId=" + userId + "]";
	}
}
