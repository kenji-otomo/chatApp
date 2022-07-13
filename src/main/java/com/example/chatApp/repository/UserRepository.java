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
	 * 主キーによるユーザ検索
	 * @param user
	 * @return　user 
	 */
	public User searchUser(User user);
	
	/**
	 * メールによるユーザ検索（対象1人）
	 * @param user
	 * @return
	 */
	public User searchUserByMail(User user);
	
	/**
	 * ユーザをインサートする
	 * @param user
	 * @return user (自動採番されたPK入り)
	 */
	public void addUser(User user);
	
	/**
	 * ユーザを更新する（セットされた項目のみを更新）
	 * @param user
	 */
	public void updateUser(User user);
}
