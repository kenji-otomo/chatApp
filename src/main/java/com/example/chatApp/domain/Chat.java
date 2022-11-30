package com.example.chatApp.domain;

import java.time.LocalDateTime;

public class Chat {
	
	private Integer chatId;
	private Integer userFk;
	private String content;
	private String picturePath;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
	private boolean deleted;
	private String roomFk;
	private String userName;
	private Integer userPictureFk;
	
	public Chat() {
		super();
	}
	public Chat(Integer userFk, String content, String roomFk) {
		super();
		this.userFk = userFk;
		this.content = content;
		this.roomFk = roomFk;
	}
	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	public Integer getUserFk() {
		return userFk;
	}
	public void setUserFk(Integer userFk) {
		this.userFk = userFk;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
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
	public String getRoomFk() {
		return roomFk;
	}
	public void setRoomFk(String roomFk) {
		this.roomFk = roomFk;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserPictureFk() {
		return userPictureFk;
	}
	public void setUserPictureFk(Integer userPictureFk) {
		this.userPictureFk = userPictureFk;
	}
	@Override
	public String toString() {
		return "Chat [chatId=" + chatId + ", userFk=" + userFk + ", content=" + content + ", picturePath=" + picturePath
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", deleted=" + deleted + ", roomFk="
				+ roomFk + ", userName=" + userName + ", userPictureFk=" + userPictureFk + "]";
	}
}
