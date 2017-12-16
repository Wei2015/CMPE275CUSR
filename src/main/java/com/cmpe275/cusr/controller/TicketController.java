package com.cmpe275.cusr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cmpe275.cusr.model.Booking;
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
	
	@Autowired
	TicketService ticketService;
	
	@PostMapping("/purchaseConfirm")
	public String purchase(Model model, @ModelAttribute Booking booking) {
		User user = userService.findUser();
		model.addAttribute("email", user.getEmail());
		boolean purchaseRes = ticketService.purchase(user, booking);
		/*model.addAttribute("purchaseRes", purchaseRes == true ? "success" : "fail");
		if (purchaseRes) {
			model.addAttribute("numOfSeats", booking.getNumOfSeats());
			model.addAttribute("passenger", booking.getPassenger());
			model.addAttribute("totalPrice", booking.getPrice() + 1);
			model.addAttribute("departureDate", booking.getDepartureDate());
			model.addAttribute("departureTrip", booking.getDepartureTrip());
			String returnDate = booking.getReturnDate();
			String isRound = (returnDate != null && !returnDate.isEmpty()) ? "Y" : "N";
			model.addAttribute("round", isRound);
			if (isRound.equals("Y")) {
				model.addAttribute("returnDate", returnDate);
				model.addAttribute("returnTrip", booking.getReturnTrip());
			}
		}
		return "purchaseConfirm";*/
		if (purchaseRes) {
			model.addAttribute("numOfSeats", booking.getNumOfSeats());
			model.addAttribute("passenger", booking.getPassenger());
			model.addAttribute("totalPrice", booking.getPrice() + 1);
			model.addAttribute("departureDate", booking.getDepartureDate());
			model.addAttribute("departureTrip", booking.getDepartureTrip());
			String returnDate = booking.getReturnDate();
			String isRound = (returnDate != null && !returnDate.isEmpty()) ? "Y" : "N";
			model.addAttribute("round", isRound);
			if (isRound.equals("Y")) {
				model.addAttribute("returnDate", returnDate);
				model.addAttribute("returnTrip", booking.getReturnTrip());
			}
			return "purchaseConfirm";
		} else {
			return "puchaseFail";
		}
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
	}
	
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
