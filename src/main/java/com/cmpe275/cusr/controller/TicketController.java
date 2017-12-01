package com.cmpe275.cusr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	TicketService ticketService;
	
	@PostMapping("/purchase")
	public String purchase(Model model, @RequestBody Booking booking) {
		long userId = userService.getUerId();
		ticketService.purchase(userId, booking);
		model.addAttribute("userId", userId);
		model.addAttribute("departureDate", booking.getDepartureDate());
		model.addAttribute("departureTime", booking.getDepartureTime().get(0));
		model.addAttribute("stop1Time", booking.getDepartureTime().get(1));
		model.addAttribute("stop2Time", booking.getDepartureTime().get(2));
		model.addAttribute("arrivalTime", booking.getArrivalTime());
		model.addAttribute("departureStation", booking.getStation().get(0));
		model.addAttribute("stop1Station", booking.getStation().get(1));
		model.addAttribute("stop2Station", booking.getStation().get(2));
		model.addAttribute("arrivalStation", booking.getArrivalStation());
		model.addAttribute("departureTrain", booking.getTrainId().get(0));
		model.addAttribute("stop1Train", booking.getTrainId().get(1));
		model.addAttribute("stop2Train", booking.getTrainId().get(2));
		model.addAttribute("numOfSeats", booking.getNumOfSeats());
		model.addAttribute("price", booking.getPrice());
		return "purchase";
	}
	
	@PostMapping("/ticketCancel")
	public String cancel(Model model, @RequestBody Booking booking) {
		model.addAttribute("UserId", userService.getUerId());
		return "ticketCancel";
	}*/
}
