package com.example.chatApp.form;

public class UpdateUserForm {

	private Integer userId;
	private String name;
	private String mail;
	private String pass;
	private String confirmPass;
	
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
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	@Override
	public String toString() {
		return "UpdateUserForm [userId=" + userId + ", name=" + name + ", mail=" + mail + ", pass=" + pass
				+ ", confirmPass=" + confirmPass + "]";
	}
}
