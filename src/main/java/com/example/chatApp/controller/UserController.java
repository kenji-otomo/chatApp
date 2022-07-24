package com.example.chatApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.example.chatApp.form.UpdateUserForm;
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
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public AddUserForm setUpAddUserForm() {
		return new AddUserForm();
	}
	@ModelAttribute
	private UpdateUserForm setUpUpdateUserForm() {
		return new UpdateUserForm();
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
			model.addAttribute("message", "パスワードが一致していません");
			return "createUser";
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		user = userService.stripUser(user);
		List<String> messageList = userService.validationCheck(user);
		if (!messageList.isEmpty()) {
			model.addAttribute("message", messageList);
			return "createUser";
		}
		
		// メールアドレス重複確認
		User dbUser = userService.searchUserByMail(user);
		if (dbUser != null) {
			model.addAttribute("message", "このメールアドレスは既に使用されています");
			return "createUser";
		}
		
		user = userService.addUser(user);
		return "redirect:/";
	}
	
	@GetMapping("/updateUser")
	public String showUpdateUser(Model model) {
		model.addAttribute("homeId",homeId);
		return "updateUser";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(UpdateUserForm form,Model model) {
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		user = userService.stripUser(user);
		List<String> messageList = userService.validationCheck(user);
		if (!messageList.isEmpty()) {
			model.addAttribute("message", messageList);
			return "updateUser";
		}
		
		user = userService.updateUser(user);
		
		session.setAttribute("user", user); 
		return "redirect:/";
	}
}
