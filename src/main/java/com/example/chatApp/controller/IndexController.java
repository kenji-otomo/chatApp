package com.example.chatApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/page")
	public String index() {
		return "home";
	}
}
