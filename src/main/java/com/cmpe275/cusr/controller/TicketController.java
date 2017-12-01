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
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	/*@PostMapping("/purchase")
	public String purchase(Model model, @RequestBody Booking booking) {
		model.addAttribute("UserName", userService.getUerId());
		return "purchase";
	}
	
	@PostMapping("/cancel")
	public String cancel() {
		
	}*/
}
