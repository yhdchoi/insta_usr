package com.yhdc.untact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MpaUsrHomeController {

	@RequestMapping("/")
	public String showMainRoot() {
		return "redirect:/usr/home/main";
	}

	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "usr/home/main";
	}
}
