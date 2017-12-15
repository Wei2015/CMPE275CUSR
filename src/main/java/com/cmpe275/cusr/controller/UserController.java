package com.cmpe275.cusr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cmpe275.cusr.model.OneWayList;
import com.cmpe275.cusr.model.SearchContent;
import com.cmpe275.cusr.service.TrainService;
import com.cmpe275.cusr.service.UserService;


@Controller
@SessionAttributes(value={"oneWayList", "searchContent"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrainService trainService;
	
	@GetMapping("/signin")
	public String signinSuccessGet(Model model, @RequestParam(value="firebaseToken", required=true) String firebaseToken) {
		
		userService.signInAuthentication(firebaseToken);
		
		return "redirect:/tickets";
	}
	
	@GetMapping("/search")
	public String index(Model model) {
		SearchContent search = new SearchContent();
		model.addAttribute("searchContent", search);
		return "search";
	}
	
	//show search results after login
	@PostMapping("/search")
	public String searchTrip(@ModelAttribute SearchContent search, Model model) {
		
				//test for trainServiceImpl
				OneWayList dbResult = trainService.searchOneWay(search);
				model.addAttribute("oneWayList", dbResult);
				
				//add search inquiry in the view
				model.addAttribute("searchContent", search);
				return "searchResult";
	}
	
	@PostMapping("/book")
	public String bookingConfirm(@ModelAttribute("oneWayList") OneWayList oneWayList, 
								@ModelAttribute("searchContent") SearchContent search,
								@RequestParam("Select") String selectTrip, Model model) {
		int tripIndexSelected = Integer.valueOf(selectTrip.substring(4, 5))-1;
		model.addAttribute(oneWayList.getFirstFive().get(tripIndexSelected));
		
		
		OneWayList returnResult = trainService.searchOneWay(search.getReturnSearch());
		model.addAttribute("returnWayList", returnResult);
		
		return "selectReturn";
	}
	
}
