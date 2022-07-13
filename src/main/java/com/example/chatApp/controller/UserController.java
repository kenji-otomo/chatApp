package com.example.chatApp.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.chatApp.domain.User;
import com.example.chatApp.form.AddUserForm;
import com.example.chatApp.service.UserService;

/**
 * ユーザ登録に関するコントローラー
 * @author ootomokenji
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	// ホーム画面のRoomID
	@Value("${home.id}")
	private String homeId;

	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public AddUserForm setUpAddUserForm() {
		return new AddUserForm();
	}
	
	/**
	 * ユーザ登録画面に遷移する
	 * @return　ユーザ登録画面
	 */
	@GetMapping("/createUser")
	public String showCreateUser() {
		return "createUser";
	}
	
	/**
	 * ユーザをDBへ登録する
	 * @param form　画面で入力されたユーザ情報
	 * @param result　エラー判定
	 * @return　チャット画面
	 */
	@PostMapping("/createUser")
	public String createUser(@Validated AddUserForm form, BindingResult result, Model model) {
		
		// バリデーションに引っかかった場合
		if (result.hasErrors()) {
			return "createUser";
		}
		// パスワードが一致しなかった場合
		if (!form.getPass().equals(form.getConfirmPass())) {
			model.addAttribute("passerror", "パスワードが一致していません");
			return "createUser";
		}
		
		
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user = userService.addUser(user);
		return "redirect:/";
	}
	
}
