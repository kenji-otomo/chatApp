package com.example.chatApp.form;

public class CreateRoomForm {

	private String roomName;
	private Integer userId;
	private Integer privateUserFk;
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPrivateUserFk() {
		return privateUserFk;
	}
	public void setPrivateUserFk(Integer privateUserFk) {
		this.privateUserFk = privateUserFk;
	}
	@Override
	public String toString() {
		return "CreateRoomForm [roomName=" + roomName + ", userId=" + userId + ", privateUserFk=" + privateUserFk + "]";
	}
}
