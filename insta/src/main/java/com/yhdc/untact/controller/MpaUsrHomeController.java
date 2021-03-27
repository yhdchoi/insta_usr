package com.yhdc.untact.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MpaUsrHomeController {

		@RequestMapping("/mpaUsr/home/main")
		@ResponseBody
		public String showMain() {
			return "HelloWorld";
		}
		
		@RequestMapping("/mpaUsr/home/main2")
		@ResponseBody
		public int showMain2(int a, int b) {
			return a + b;
		}
		
		@RequestMapping("/mpaUsr/home/main3")
		@ResponseBody
		public Map<String, Object> showMain3() {
			Map<String, Object> map = new HashMap<>();
			map.put("age1", 22);
			map.put("age2", 33);
			
			return map;
		}
		
		
		
}
