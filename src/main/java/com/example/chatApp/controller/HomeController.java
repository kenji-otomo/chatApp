package com.example.chatApp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.chatApp.domain.Chat;
import com.example.chatApp.domain.Room;
import com.example.chatApp.form.CreateRoomForm;
import com.example.chatApp.service.ChatService;
import com.example.chatApp.service.RoomService;


@Controller
@RequestMapping("/home")
public class HomeController {
	
	// ホーム画面のRoomID
	@Value("${home.id}")
	private String homeId;
	
	@ModelAttribute
	public CreateRoomForm setUpCreateRoomForm() {
		return new CreateRoomForm();
	}

	@Autowired
	private HttpSession session;
	@Autowired
	private ChatService chatService;
	@Autowired
	private RoomService roomService;
	
	/**
	 * チャットルームに入室します
	 * @param roomId
	 * @param model
	 * @param key
	 * @return
	 */
	@RequestMapping("room/{roomId}")
	public String page(@PathVariable String roomId, Model model) {
		
		Room room = new Room();
		room.setRoomId(roomId);
		
		if (!roomId.equals(homeId)) {
			room = roomService.judgeUserInRoom(room);
		}

		// ユーザーがルームに存在していない場合、ホームに戻す
		if (!roomId.equals(homeId) && room == null) {
			roomId = homeId;
			model.addAttribute("message", "入室権限がありません");
		}
		
		String privateKey = "privateList";
		String publicKey = "publicList";
		
		// RoomIDをセット
		Chat chat = new Chat(null,null,roomId);
		// RoomID に紐づいているチャットのリストを検索
		List<Chat> chatList = chatService.searchChatByRoomId(chat);
		// ログインしているユーザが所属しているルームのリストを検索
		Map<String, List<Room>> roomMap = roomService.searchRoomInLoginUser(roomId);
		List<Room> privateList = roomMap.get(privateKey);
		List<Room> publicList = roomMap.get(publicKey);
		// リクエストスコープに埋め込み
		model.addAttribute("chatList", chatList);
		model.addAttribute("homeId", homeId);
		model.addAttribute("room", new Room(roomId));
		model.addAttribute("privateList", privateList);
		model.addAttribute("publicList", publicList);
		return "home";
	}
	
	/**
	 * ルームを作成します
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/createRoom")
	public String createRoom(CreateRoomForm form, Model model) {
		
		Room room = new Room();
		// 個人チャットかどうか
		if (form.getPrivateUserFk() == null) {
			// 公開部屋の場合、ルーム名に入力された名前をセット
			room.setRoomName(form.getRoomName());
		}else {
			// 個人チャットの場合、privateRoom を true にする
			room.setPrivateRoom(true);
		}
		// ルームを作成する
		String roomId = roomService.createRoom(room,form.getUserId(),form.getPrivateUserFk());
		
		return page(roomId, model);
	}

	

	@RequestMapping("/logout")
	public String logout() {
		
		session.removeAttribute("user");
		return "redirect:/";
	}
}
