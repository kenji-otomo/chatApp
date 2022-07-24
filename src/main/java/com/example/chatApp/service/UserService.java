package com.example.chatApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chatApp.domain.User;
import com.example.chatApp.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		userRepository.addUser(user);
		return user;
	}
	
	public User updateUser(User user) {
		
		stripUser(user);
		userRepository.updateUser(user);
		user = userRepository.searchUser(user);
		return user;
	}
	
	public User searchUserByMail(User user) {
		user = userRepository.searchUserByMail(user);
		return user;
	}
	
	public User stripUser(User user) {
		
		if (user.getName() != null) {
			String name = user.getName().strip();
			
			if (name.equals("")) {
				user.setName(null);
			}else {
				user.setName(name);
			}
		}
		if (user.getMail() != null) {
			String mail = user.getMail().strip();
			if (mail.equals("")) {
				user.setMail(null);
			}else {
				user.setMail(mail);
			}
		}
		if (user.getPass() != null) {
			String pass = user.getPass().strip();
			if (pass.equals("")) {
				user.setPass(null);
			}else {
				user.setPass(pass);
			}
		}
		
		return user;
	}
	
	public List<String> validationCheck(User user) {
		List<String> messageList = new ArrayList<>();
		if (user.getName() == null) {
			messageList.add("名前は必須入力項目です");
		}
		if (user.getMail() == null) {
			messageList.add("メールアドレスは必須入力項目です");
		}
		if (user.getPass() == null) {
			messageList.add("パスワードは必須入力項目です");
		}else if (8 > user.getPass().length() || user.getPass().length() > 16) {
			messageList.add("パスワードは8文字以上16文字以内で入力してください");
		}
		
		return messageList;
	}
}
