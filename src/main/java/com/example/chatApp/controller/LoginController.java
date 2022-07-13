package com.example.chatApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.chatApp.domain.User;
import com.example.chatApp.form.LoginForm;
import com.example.chatApp.service.LoginService;

@Controller
@RequestMapping("")
public class LoginController {
	
	// ホーム画面のRoomID
	@Value("${home.id}")
	private String homeId;
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private HttpSession session;

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * ログイン画面を表示します
	 * @return
	 */
	@RequestMapping("")
	public String showLogin() {
		return "login";
	}
	
	/**
	 * ログイン処理をします
	 * @return
	 */
	@PostMapping("/login")
	public String login(@Validated LoginForm form, BindingResult result, Model model) {
		// バリデーションチェック
		if (result.hasErrors()) {
			model.addAttribute("message", "バリデーションに引っ掛かりました");
			return "login";
		}
		// DBのユーザ検索
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user =	loginService.login(user);

		// 有効なユーザが存在しない場合、ログイン画面に戻る
		if (user == null) {
			model.addAttribute("message", "ユーザが存在しません");
			return "login";
		}
		// 入力されたパスワードが保存されているパスワードと一致しない場合
		if (!form.getPass().equals(user.getPass())) {
			model.addAttribute("message", "パスワードが異なっています");			
			return "login";
		}
		// セッションスコープにユーザ情報を格納
		session.setAttribute("user", user);
		
		return "redirect:/home/room/"+homeId;
	}
}
