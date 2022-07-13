package com.example.chatApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.chatApp.domain.Chat;
import com.example.chatApp.service.ChatService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	// ホーム画面のRoomID
	@Value("${home.id}")
	private String homeId;

	@Autowired
	private HttpSession session;
	@Autowired
	private ChatService chatService;
	
//	@RequestMapping("")
//	public String home() {
//		return "home";
//	}
	
	@RequestMapping("room/{roomId}")
	public String page(@PathVariable String roomId,Model model) {
		System.out.println("roomID:"+roomId);
		Chat chat = new Chat();
		chat.setRoomFk(roomId);
		List<Chat> chatList = chatService.searchChatByRoomId(chat);
		System.out.println(chatList);
		model.addAttribute("chatList", chatList);
		model.addAttribute("homeId",homeId);
		model.addAttribute("roomId",roomId);		
		return "home";
	}
	
	@RequestMapping("/enter")
	public String enterTheRoom(String roomId) {
		System.out.println("enter");
		return "redirect:/home/room/"+roomId;
	}
	
	@RequestMapping("/logout")
	public String logout() {
		
		session.removeAttribute("user");
		return "redirect:/";
	}
}
