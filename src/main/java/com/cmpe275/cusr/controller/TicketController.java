package com.cmpe275.cusr.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.service.TicketService;
import com.cmpe275.cusr.service.UserService;

@Controller
public class TicketController {
	
	 /*@Autowired 
	 UserService userService;
	 
	 @Autowired 
	 TicketService ticketService;

	@PostMapping("/purchase")
	public String purchase(Model model, @ModelAttribute Booking booking) {
		/*
		 * public String purchase(Model model) { long userId = 1; String round = "v";
		 * model.addAttribute("userId", userId); model.addAttribute("round", round);
		 * List<Integer> trains = new ArrayList<>(); trains.add(1); trains.add(2);
		 * trains.add(3); model.addAttribute("trains", trains); List<Integer> times =
		 * new ArrayList<>(); times.add(4); times.add(5); times.add(6);
		 * model.addAttribute("times", times); return "purchase"; }
		 */
		/*User user = userService.findUser();
		if (ticketService.purchase(user, booking)) {
			Date returnDate = booking.getReturnDate();
			model.addAttribute("firstName", user.getFirstName());
			model.addAttribute("lastName", user.getLastName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("numOfSeats", booking.getNumOfSeats());
			model.addAttribute("price", booking.getPrice());
			model.addAttribute("departureDate", booking.getDepartureDate());
			model.addAttribute("departureTrip", booking.getDepartureTrip());
			model.addAttribute("returnDate", returnDate);
			model.addAttribute("returnTrip", booking.getReturnTrip());
			model.addAttribute("round", returnDate == null ? "Y" : "N");
			return "purchase_success";
		} else {
			return "purchase_fail";
		}

	}*/

	/*
	 * @PostMapping("/ticketCancel") public String cancel(Model model, @RequestBody
	 * Booking booking) { model.addAttribute("UserId", userService.getUerId());
	 * return "ticketCancel"; }
	 */
}
