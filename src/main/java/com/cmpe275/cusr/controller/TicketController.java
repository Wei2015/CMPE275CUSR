package com.cmpe275.cusr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.Ticket;
import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.repository.TicketRepository;
import com.cmpe275.cusr.service.TicketService;
import com.cmpe275.cusr.service.UserService;

@Controller
public class TicketController {

	@Autowired
	UserService userService;
	
	@Autowired
	TicketRepository ticketRepository;
	
	/*private List<Segment> departureTrip = new ArrayList<Segment>();
	public TicketController() {
		//departureTrip.add(new Segment(S, ))
	}
	/*
	 * public String purchase(Model model) { long userId = 1; String round = "v";
	 * model.addAttribute("userId", userId); model.addAttribute("round", round);
	 * List<Integer> trains = new ArrayList<>(); trains.add(1); trains.add(2);
	 * trains.add(3); model.addAttribute("trains", trains); List<Integer> times =
	 * new ArrayList<>(); times.add(4); times.add(5); times.add(6);
	 * model.addAttribute("times", times); return "purchase"; }
	 */
	
	/*@PostMapping("/purchase")
	public String select(Model model, @ModelAttribute Booking booking) { 
		int numOfSeats = booking.getNumOfSeats();
		model.addAttribute("numOfSeats", numOfSeats);
		List<String> passenger = new ArrayList<>();
		for (int i = 0; i < numOfSeats; ++i) {
			passenger.add("");
		}
		model.addAttribute("passenger", passenger);
		double price = booking.getPrice();
		model.addAttribute("price", price);
		model.addAttribute("totalPrice", price + 1);
		model.addAttribute("departureDate", booking.getDepartureDate());
		model.addAttribute("departureTrip", booking.getDepartureTrip());
		String returnDate = booking.getReturnDate();
		model.addAttribute("round", returnDate == null ? "Y" : "N");
		if (returnDate != null) {
			model.addAttribute("returnDate", returnDate);
			model.addAttribute("returnTrip", booking.getReturnTrip());
		}
		return "purchase";
	}

	@PostMapping("/purchaseConfirm")
	public String purchase(Model model, @ModelAttribute Booking booking) {
		User user = userService.findUser();
		model.addAttribute("email", user.getEmail());
		boolean purchaseRes = ticketService.purchase(user, booking);
		model.addAttribute("purchaseRes", purchaseRes == true ? "success" : "fail");
		if (purchaseRes) {
			model.addAttribute("numOfSeats", booking.getNumOfSeats());
			model.addAttribute("passenger", booking.getPassenger());
			model.addAttribute("totalPrice", booking.getPrice() + 1);
			model.addAttribute("departureDate", booking.getDepartureDate());
			model.addAttribute("departureTrip", booking.getDepartureTrip());
			String returnDate = booking.getReturnDate();
			model.addAttribute("round", returnDate == null ? "Y" : "N");
			if (returnDate != null) {
				model.addAttribute("returnDate", returnDate);
				model.addAttribute("returnTrip", booking.getReturnTrip());
			}
		}
		return "purchaseConfirm";
	}

	@PostMapping("/ticketCancel")
	public String cancel(Model model, @ModelAttribute long ticketId) {
		User user = userService.findUser();
		model.addAttribute("email", user.getEmail());
		if (ticketService.cancel(ticketId)) {
			return "ticketCancel_sucess";
		} else {
			return "ticketCancel_fail";
		}
	}*/
	
	@GetMapping("/tickets")
	public String showUserTickets(Model model) {
		long userId = userService.findUser().getUserId();
		model.addAttribute("ticketList", ticketRepository.findTicketsByUserId(userId));
		return "usertickets";
	}
	
	@GetMapping("/ticketDetails/{id}")
	public String ticketDetails(@PathVariable("id") long id, Model model) {
		model.addAttribute("ticket", ticketRepository.findOne(id));
		return "usertickets :: modalContents";
	}
}
