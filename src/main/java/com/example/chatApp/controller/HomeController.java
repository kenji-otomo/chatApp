package com.example.chatApp.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	// ルームに入室できる権限を判定するキー
	private String primaryKey = "";
	
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
	
	@RequestMapping("room/{roomId}")
	public String page(@PathVariable String roomId,Model model,String key) {
		String privateKey = "privateList";
		String publicKey = "publicList";
		
		if (!roomId.equals(homeId) && !primaryKey.equals(key)) {
			roomId = homeId;
			model.addAttribute("message", "権限がありません");
		}
		
		// RoomIDをセット
		Chat chat = new Chat(null,null,roomId);
		// RoomID に紐づいているチャットのリストを検索
		List<Chat> chatList = chatService.searchChatByRoomId(chat);
		// ログインしているユーザが所属しているルームのリストを検索
		Map<String, List<Room>> roomMap = roomService.searchRoomByUserId(roomId);
		List<Room> privateList = roomMap.get(privateKey);
		List<Room> publicList = roomMap.get(publicKey);
		// リクエストスコープに埋め込み
		model.addAttribute("chatList", chatList);
		model.addAttribute("homeId",homeId);
		model.addAttribute("room",new Room(roomId));
		model.addAttribute("privateList", privateList);
		model.addAttribute("publicList", publicList);
		
		return "home";
	}
	
	@PostMapping("/createRoom")
	public String createRoom(CreateRoomForm form,Model model) {
		
		Room room = new Room();
		
		if (form.getPrivateUserFk() == null) {
			room.setRoomName(form.getRoomName());
		}else {
			room.setPrivateRoom(true);
		}
		String roomId = roomService.createRoom(room,form.getUserId(),form.getPrivateUserFk());
		
		return enterTheRoom(roomId, model);
	}

	@RequestMapping("/enter")
	public String enterTheRoom(String roomId,Model model) {

		Room room = new Room();
		room.setRoomId(roomId);
		room = roomService.searchRoomByRoomId(room);
		
		primaryKey = UUID.randomUUID().toString();
		
		if (room == null) {
			model.addAttribute("message", "権限がありません");
			return page(homeId, model,primaryKey);
		}
		return page(roomId, model,primaryKey);
	}

	@RequestMapping("/logout")
	public String logout() {
		
		session.removeAttribute("user");
		return "redirect:/";
	}
}
