package com.example.chatApp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.chatApp.domain.Room;
import com.example.chatApp.domain.User;
import com.example.chatApp.repository.RoomRepository;
import com.example.chatApp.repository.UserInRoomRepository;

@Service
@Transactional
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserInRoomRepository userInRoomRepository;
	@Autowired
	private HttpSession session;
	
	/**
	 * ルーム作成
	 * @param room
	 * @param userId
	 * @param privateUserFk
	 * @return
	 */
	public String createRoom(Room room,Integer userId, Integer privateUserFk) {
		// UUIDによって重複のないルームIDを作成し、ルームドメインにセット
		String roomId = UUID.randomUUID().toString();
		room.setRoomId(roomId);
		// ルームを作成
		roomRepository.createRoom(room);
		// ルームにログインユーザを紐付けを行う
		userInRoomRepository.createUIR(roomId, userId, false);
		// プライベートルームの場合、相手も紐付けを行う
		if (privateUserFk != null) {
			userInRoomRepository.createUIR(roomId, privateUserFk, false);
		}
		// 生成されたルームIDを返す
		return roomId;
	}
	
	/**
	 * ルームにログインユーザが存在するかを判別
	 * @param room
	 * @return
	 */
	public Room judgeUserInRoom(Room room) {
		room = roomRepository.searchRoomByRoomId(room);
		User user = (User)session.getAttribute("user");
		
		if (room == null) {
			return null;
		}

		List<User>userList = room.getRoomUserList();
		for (User listUser : userList) {
			
			if (listUser.getUserId() == user.getUserId()) {
				return room;
			}
		}
		return null;
	}

	/**
	 * ログインしているユーザが所属しているルームのリストを返す
	 * @param roomId
	 * @return
	 */
	public Map<String, List<Room>> searchRoomInLoginUser(String roomId) {
		Map<String, List<Room>> returnMap = new HashMap<>();
		// セッションからログイン中のユーザ情報を取得
		User user = (User)session.getAttribute("user");
		// ログイン中のユーザに紐づいているルームを取得
		List<Room> roomList = roomRepository.searchRoomByUserId(user);
		
		List<Room> publicList = new ArrayList<>();
		List<Room> privateList = new ArrayList<>();
		
		// 公開部屋と個人チャットを判別する
		for (Room room : roomList) {
			
			// 入室する部屋の場合、処理を飛ばす
			if (room.getRoomId().equals(roomId)) {
				continue;
			}
			
			// 公開部屋か個人チャットの場合で処理を変える
			if (!room.getPrivateRoom()) {
				// 公開部屋の場合、RoomId と RoomName をリストに追加
				publicList.add(new Room(room.getRoomId(), room.getRoomName()));
			}else {
				// 個人チャットの場合、ログイン中のユーザではないユーザの名前を追加
				setUserPrivateList(room, privateList, user);
			}
		}
		
		returnMap.put("privateList", privateList);
		returnMap.put("publicList", publicList);
		
		return returnMap;
	}
	
	/**
	 * 個人チャットの場合、ログイン中のユーザではないユーザの名前を追加
	 * @param room
	 * @param privateList
	 * @param user
	 */
	private void setUserPrivateList(Room room, List<Room> privateList, User user) {
		for (User roomUser : room.getRoomUserList()) {
			if (roomUser.getUserId() != user.getUserId()) {
				privateList.add(new Room(room.getRoomId(),roomUser.getName()));
			}
		}
	}
}
