package com.example.chatApp.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * ログインのためのフォーム
 * @author ootomokenji
 *
 */
public class LoginForm {

	@NotBlank(message = "メールアドレスは必須入力項目です")
	@Email(message = "Eメールの形式で入力してください")
	private String mail;
	@NotBlank(message = "パスワードは必須入力項目です")
	@Size(min = 8,max = 16,message = "パスワードは8文字以上16文字以内で入力してください")
	private String pass;
	
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
	@Override
	public String toString() {
		return "LoginForm [mail=" + mail + ", pass=" + pass + "]";
	}
}
