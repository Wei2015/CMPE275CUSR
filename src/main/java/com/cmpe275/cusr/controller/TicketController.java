package com.cmpe275.cusr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.service.TicketService;
import com.cmpe275.cusr.service.UserService;

@Controller
public class TicketController {
	
	/*@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;*/
	
	@GetMapping("/purchase")
	//public String purchase(Model model, @ModelAttribute("booking") Booking booking) {
	public String purchase(Model model) {
		long userId = 1;
		model.addAttribute("userId", userId);
		List<Integer> trains = new ArrayList<>();
		trains.add(1);
		trains.add(2);
		trains.add(3);
		model.addAttribute("trains", trains);
		List<Integer> times = new ArrayList<>();
		times.add(4);
		times.add(5);
		times.add(6);
		model.addAttribute("times", times);
		/*long userId = userService.getUerId();
		ticketService.purchase(userId, booking);
		model.addAttribute("userId", userId);
		model.addAttribute("departureDate", booking.getDepartDate());
		model.addAttribute("departureTime", booking.getDepartTime().get(0));
		model.addAttribute("stop1Time", booking.getDepartTime().get(1));
		model.addAttribute("stop2Time", booking.getDepartTime().get(2));
		model.addAttribute("arrivalTime", booking.getArrivalTime());
		model.addAttribute("departureStation", booking.getStation().get(0));
		model.addAttribute("stop1Station", booking.getStation().get(1));
		model.addAttribute("stop2Station", booking.getStation().get(2));
		model.addAttribute("arrivalStation", booking.getArrivalStation());
		model.addAttribute("departureTrain", booking.getTrainId().get(0));
		model.addAttribute("stop1Train", booking.getTrainId().get(1));
		model.addAttribute("stop2Train", booking.getTrainId().get(2));
		model.addAttribute("numOfSeats", booking.getNumOfSeats());
		model.addAttribute("price", booking.getPrice());*/
		return "purchase";
	}
	
	/*@PostMapping("/ticketCancel")
	public String cancel(Model model, @RequestBody Booking booking) {
		model.addAttribute("UserId", userService.getUerId());
		return "ticketCancel";
	}*/
}
