package com.example.chatApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chatApp.domain.User;
import com.example.chatApp.repository.UserRepository;

@Service
@Transactional
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	
	public User login(User user) {
		// DBのユーザを検索
		user = userRepository.searchUserByMail(user);
		// DBにユーザが存在しない　または　削除済みユーザの場合
		if (user == null || user.isDeleted()) {
			return null;
		}
		
		return user;
	}
}
