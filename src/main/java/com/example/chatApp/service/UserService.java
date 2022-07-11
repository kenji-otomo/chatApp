package com.example.chatApp.service;

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
		user.setDeleted(false);
		userRepository.addUser(user);
		return user;
	}
		
	
}
