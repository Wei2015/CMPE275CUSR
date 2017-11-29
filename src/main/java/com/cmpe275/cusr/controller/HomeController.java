package com.cmpe275.cusr.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmpe275.cusr.model.SearchContent;



@Controller
public class HomeController {
	
	
	//show home page for get request on home url.
		@GetMapping("/")
		public String index(Model model) {
			SearchContent search = new SearchContent();
			model.addAttribute("searchContent", search);
			
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