package com.example.chatApp.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.chatApp.domain.User;

/**
 * ユーザに関するマッパー
 * @author ootomokenji
 *
 */
@Mapper
public interface UserRepository {

	/**
	 * 一人のユーザ検索（主キー）
	 * @param user
	 * @return　user 
	 */
	public User searchUser(User user);
	
	/**
	 * ユーザをインサートする
	 * @param user
	 * @return user (自動採番されたPK入り)
	 */
	public User addUser(User user);
	
	/**
	 * ユーザを更新する（セットされた項目のみを更新）
	 * @param user
	 */
	public void updatedUser(User user);
}
