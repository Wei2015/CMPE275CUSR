package com.cmpe275.cusr.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cmpe275.cusr.model.OneWayList;
import com.cmpe275.cusr.model.OneWayTrip;
import com.cmpe275.cusr.model.SearchContent;
import com.cmpe275.cusr.model.Segment;
import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.service.TrainService;




@Controller
public class HomeController {
	
	@Autowired
	private TrainService trainService;

	
	//show home page for get request on home url.
	@GetMapping("/")
	
	
	//show search results without login
	@PostMapping("/")
	public String searchTrip(@ModelAttribute SearchContent search, Model model) {
		
				//test for trainServiceImpl
				OneWayList dbResult = trainService.searchOneWay(search);
				model.addAttribute("oneWayList", dbResult);
				
				
				
				//add search inquiry in the view
				model.addAttribute("searchContent", search);
				return "searchResult";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "userlogin";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model) {
		return "userreg";
	}
	
	
	/* No need for admin login
	@RequestMapping("/adminlogin")
	public String adminlogin(Model model) {
		return "adminlogin";
	}
	*/
	
	@RequestMapping("/user/tickets")
	public String showUserTickets(Model model) {
		return "usertickets";
	}

}