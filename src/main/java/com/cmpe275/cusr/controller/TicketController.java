package com.cmpe275.cusr.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.Ticket;
import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.repository.TicketRepository;
import com.cmpe275.cusr.service.EmailService;
import com.cmpe275.cusr.service.TicketService;
import com.cmpe275.cusr.service.UserService;

@Controller
public class TicketController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private EmailService emailService;
	
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
		String content = emailService.mailBuilder(booking);
		emailService.sendMail(user.getEmail(),"CUSR Ticket Booking Confirmation", content);
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
			String msg = "Thanks for your booking! Here is the ticket details:";
			String content = emailService.bookingMailBuilder(booking, "emailTemplateBook", msg);
			emailService.sendMail(user.getEmail(),"CUSR Ticket Booking Confirmation", content);
			return "purchaseConfirm";
		} else {
			String msg = "Sorry, we could not proceed with the following booking. Please try your search again!";
			String content = emailService.bookingMailBuilder(booking, "emailTemplateBook", msg);
			emailService.sendMail(user.getEmail(),"CUSR Ticket Booking Fail", content);
			return "puchaseFail";
		}
	}

	@GetMapping("/ticketCancel")
	public String cancel(@RequestParam("id") long ticketId, Model model) {
		Ticket ticket = ticketRepository.findOne(ticketId);
		model.addAttribute("numOfSeats", ticket.getNumOfSeats());
		model.addAttribute("passenger", ticket.getPassenger());
		model.addAttribute("totalPrice", ticket.getPrice());
		model.addAttribute("departureDate", ticket.getDepartDate());
		model.addAttribute("departureTime", ticket.getDepartSegment1DepartTime());
		model.addAttribute("departureStation", ticket.getDepartStation());
		model.addAttribute("arrivalStation", ticket.getArrivalStation());
		String returnDate = ticket.getReturnDate();
		String isRound = (returnDate != null && !returnDate.isEmpty()) ? "Y" : "N";
		model.addAttribute("round", isRound);
		if (isRound.equals("Y")) {
			model.addAttribute("returnDate", returnDate);
			model.addAttribute("returnTime", ticket.getReturnSegment1DepartTime());
		}
		User user = userService.findUser();
		if (ticketService.cancel(ticketId)) {
			String msg = "The following booking has been successfully cancelled!";
			model.addAttribute("message", msg);
			String content = emailService.ticketMailBuilder(ticketId, "emailTemplateCancel", msg);
			emailService.sendMail(user.getEmail(),"CUSR Ticket Cancellation Confirmation", content);
		} else {
			String msg = "The following booking cannot be cancelled. Please cancel your ticket(s) earlier.";
			model.addAttribute("message", msg);
			String content = emailService.ticketMailBuilder(ticketId, "emailTemplateCancel", msg);
			emailService.sendMail(user.getEmail(),"CUSR Ticket Cancellation Fail", content);			
		}
		return "ticketCancel";
	}
	
	@GetMapping("/tickets")
	public String showUserTickets(Model model) {
		long userId = userService.findUser().getUserId();
		ArrayList<Ticket> qRes = ticketRepository.findTicketsByUserId(userId);
		ArrayList<Ticket> ticketList = new ArrayList<>();
		for(Ticket ticket : qRes) {
			if(ticketService.timeCheck(ticket.getDepartDate(), ticket.getDepartSegment1ArrivalTime(), 0))
				ticketList.add(ticket);
		}
		model.addAttribute("ticketList", ticketList);
		return "usertickets";
	}
}
