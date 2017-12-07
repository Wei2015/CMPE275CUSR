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
	public String index(Model model) {
		SearchContent search = new SearchContent();
		model.addAttribute("searchContent", search);
		return "home";
	}
	
	//show search results without login
	@PostMapping("/")
	public String searchTrip(@ModelAttribute SearchContent search, Model model) {
		//testing show List of trips found on view
		
				OneWayTrip oneWay = new OneWayTrip("12/09/2017", 3);
				Segment seg1 = new Segment("NB0800","10:23:00", "12:23:00", Station.C, Station.N);
				Segment seg2 = new Segment("NB1200","12:45:00","15:23:00", Station.N, Station.Q);
				List<Segment> connection = new ArrayList<>();
				connection.add(seg1);
				connection.add(seg2);
				oneWay.setConnections(connection);
				
				OneWayTrip oneWay2 = new OneWayTrip("12/11/2017", 3);
				Segment seg11 = new Segment("SB0915","7:25:00", "9:25:00", Station.A, Station.C);
				Segment seg22 = new Segment("SB1230","9:37:00", "10:25:00", Station.C, Station.N);
				List<Segment> connection2 = new ArrayList<>();
				connection2.add(seg11);
				connection2.add(seg22);
				oneWay2.setConnections(connection2);
				
				List<OneWayTrip> oneList = new ArrayList<>();
				oneList.add(oneWay);
				oneList.add(oneWay2);
				OneWayList testOneWayList = new OneWayList();
				testOneWayList.setFirstFive(oneList);
				//model.addAttribute("oneWayList", testOneWayList);
			
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

	@RequestMapping("/adminlogin")
	public String adminlogin(Model model) {
		return "adminlogin";
	}
	
	@RequestMapping("/user/tickets")
	public String showUserTickets(Model model) {
		return "usertickets";
	}

}