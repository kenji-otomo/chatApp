package com.example.chatApp.domain;

import java.time.LocalDateTime;

public class User {

	private Integer userId;
	private String name;
	private String mail;
	private String pass;
	private Integer pictureFk;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
	private boolean deleted;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Integer getPictureFk() {
		return pictureFk;
	}
	public void setPictureFk(Integer pictureFk) {
		this.pictureFk = pictureFk;
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", mail=" + mail + ", pass=" + pass + ", pictureFk="
				+ pictureFk + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", deleted=" + deleted
				+ "]";
	}
}
