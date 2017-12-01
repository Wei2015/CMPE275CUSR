package com.cmpe275.cusr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	//show search results without login
	@PostMapping("/")
	public String searchTrip(@ModelAttribute SearchContent search, Model model) {
		search.setDepartureDate("12-1-2017");
		//model.addAttribute("searchContent",search);
		System.out.println(search.toString());
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