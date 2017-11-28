package com.cmpe275.cusr.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	/*
	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "home";
	}
	*/
	
	@RequestMapping("/")
	public String index(Model model) {
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "userlogin";
	}

	@RequestMapping("/adminlogin")
	public String adminlogin(Model model) {
		return "adminlogin";
	}

}