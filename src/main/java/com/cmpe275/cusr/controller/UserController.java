package com.cmpe275.cusr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cmpe275.cusr.model.OneWayList;
import com.cmpe275.cusr.model.OneWayTrip;
import com.cmpe275.cusr.model.SearchContent;
import com.cmpe275.cusr.service.TrainService;
import com.cmpe275.cusr.service.UserService;


@Controller
@SessionAttributes(value={"oneWayList", "searchContent", "oneWayTrip", "returnWayList"})
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
	
	@GetMapping("/signout")
	public String signout(Model model) {
		userService.signout();
		
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String index(Model model) {
		SearchContent search = new SearchContent();
		model.addAttribute("searchContent", search);
		return "search";
	}
	
	//show search results after login
	@PostMapping("/search")
	public String searchTrip(@ModelAttribute SearchContent search, HttpServletRequest request,
			Model model) {
				
				//add search inquiry in the view
				model.addAttribute("searchContent", search);
				
				request.setAttribute("numberOfConnections", search.getNumberOfConnections());
		
				//create search result container
				OneWayList result = new OneWayList();
				
				//verify input date and time
				if (trainService.verfiyDateAndTime(search, result)) {
					//search for forward trip 
					trainService.searchOneWay(search, result);
				}
				model.addAttribute("oneWayList", result);
				
				return "searchResult";
	}
	
	@PostMapping("/selectReturn")
	public String bookingConfirm(@ModelAttribute("oneWayList") OneWayList oneWayList, 
								@ModelAttribute("searchContent") SearchContent search,
								@RequestParam("Select") String selectTrip, 
								SessionStatus status, Model model) {
		int tripIndexSelected = Integer.valueOf(selectTrip.substring(4, 5))-1;
		OneWayTrip forwardTrip = oneWayList.getFirstFive().get(tripIndexSelected);
		model.addAttribute("oneWayTrip", forwardTrip);
		
		if (search.isRoundTrip()) {
			OneWayList returnResult= new OneWayList();
			trainService.searchOneWay(search.getReturnSearch(), returnResult);
			model.addAttribute("returnWayList", returnResult);
			return "selectReturn";
		}else {
			//fill in model
			int numOfSeats = search.getNumberOfSeats();
			model.addAttribute("numOfSeats", numOfSeats);
			List<String> passenger = new ArrayList<>();
			for (int i = 0; i < numOfSeats; ++i) {
				passenger.add("");
			}
			model.addAttribute("passenger", passenger);
			
			double price= forwardTrip.getTicketPrice();
			model.addAttribute("price", price);
			model.addAttribute("totalPrice", price + 1);
			model.addAttribute("departureDate", search.getDepartureDate());
			model.addAttribute("departureTrip", forwardTrip.getConnections());
			status.setComplete();
			return "purchase";
		}
	}
	
	@PostMapping("/purchase")
	public String select(@ModelAttribute("oneWayTrip") OneWayTrip forwardTrip, 
						@ModelAttribute("searchContent") SearchContent search,
						@ModelAttribute("returnWayList") OneWayList returnWayList,
						@RequestParam("Select") String selectTrip, Model model,
						SessionStatus status) { 
		
		//obtain return trip object
		int tripIndexSelected = Integer.valueOf(selectTrip.substring(4, 5))-1;
		OneWayTrip returnTrip = returnWayList.getFirstFive().get(tripIndexSelected);
		
		//fill in model
		int numOfSeats = search.getNumberOfSeats();
		model.addAttribute("numOfSeats", numOfSeats);
		List<String> passenger = new ArrayList<>();
		for (int i = 0; i < numOfSeats; ++i) {
			passenger.add("");
		}
		model.addAttribute("passenger", passenger);
		
		double price= forwardTrip.getTicketPrice() + returnTrip.getTicketPrice();
		model.addAttribute("price", price);
		model.addAttribute("totalPrice", price + 1);
		model.addAttribute("departureDate", search.getDepartureDate());
		
		model.addAttribute("departureTrip", forwardTrip.getConnections());
		String returnDate = search.getReturnDate();
		model.addAttribute("round", search.isRoundTrip()? "Y" : "N");
		if (returnDate != null) {
			model.addAttribute("returnDate", returnDate);
			model.addAttribute("returnTrip", returnTrip.getConnections());
		}
		status.setComplete();
		return "purchase";
	}
	
	
}
