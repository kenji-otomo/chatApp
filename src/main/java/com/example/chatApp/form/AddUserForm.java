package com.example.chatApp.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddUserForm {

	@NotBlank(message = "名前は必須入力項目です")
	private String name;
	@NotBlank(message = "メールアドレスは必須入力項目です")
	@Email(message = "Emailの形式で入力して下さい")
	private String mail;
	@NotBlank(message = "パスワードは必須入力項目です")
	@Size(min = 8,max = 16,message = "パスワードは8文字以上16文字以内で入力してください")
	private String pass;
	@NotBlank(message = "確認用パスワードは必須入力項目です")
	@Size(min = 8,max = 16,message = "確認用パスワードは8文字以上16文字以内で入力してください")
	private String confirmPass;
	
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
		return "AddUserForm [name=" + name + ", mail=" + mail + ", pass=" + pass + ", confirmPass=" + confirmPass + "]";
	}
}
