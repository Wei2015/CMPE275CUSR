package com.cmpe275.cusr.controller;

import java.util.ArrayList;
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
	
	@PostMapping("/user/select")
	public String select(Model model, @ModelAttribute Booking booking) { 
		String returnDate = booking.getReturnDate();
		int numOfSeats = booking.getNumOfSeats();
		List<String> passenger = new ArrayList<>();
		for (int i = 0; i < numOfSeats; ++i) {
			passenger.add("");
		}
		model.addAttribute("passenger", passenger);
		model.addAttribute("numOfSeats", numOfSeats);
		model.addAttribute("price", booking.getPrice());
		model.addAttribute("totalPrice", booking.getPrice() + 1);
		model.addAttribute("departureDate", booking.getDepartureDate());
		model.addAttribute("departureTrip", booking.getDepartureTrip());
		model.addAttribute("round", returnDate == null ? "Y" : "N");
		if (returnDate != null) {
			model.addAttribute("returnDate", returnDate);
			model.addAttribute("returnTrip", booking.getReturnTrip());
		}
		return "select";
	}

	@PostMapping("/user/purchase")
	public String purchase(Model model, @ModelAttribute Booking booking) {
		User user = userService.findUser();
		model.addAttribute("email", user.getEmail());
		if (ticketService.purchase(user, booking)) {
			int numOfSeats = booking.getNumOfSeats();
			model.addAttribute("passenger", booking.getPassenger());
			model.addAttribute("numOfSeats", numOfSeats);
			model.addAttribute("numOfTickets", numOfSeats > 1 ? "multiple" : "single");
			model.addAttribute("totalPrice", booking.getPrice() + 1);
			model.addAttribute("departureDate", booking.getDepartureDate());
			model.addAttribute("departureTrip", booking.getDepartureTrip());
			String returnDate = booking.getReturnDate();
			model.addAttribute("round", returnDate == null ? "Y" : "N");
			if (returnDate != null) {
				model.addAttribute("returnDate", returnDate);
				model.addAttribute("returnTrip", booking.getReturnTrip());
			}
			return "purchase_success";
		} else {
			return "purchase_fail";
		}
	}

	@PostMapping("/user/ticketCancel")
	public String cancel(Model model, @ModelAttribute long ticketId) {
		User user = userService.findUser();
		model.addAttribute("email", user.getEmail());
		if (ticketService.cancel(ticketId)) {
			return "ticketCancel_sucess";
		} else {
			return "ticketCancel_fail";
		}
	}*/
}
