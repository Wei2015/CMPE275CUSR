package com.cmpe275.cusr.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.Ticket;
import com.cmpe275.cusr.repository.TicketRepository;
import com.cmpe275.cusr.repository.TrainRepository;
import com.cmpe275.cusr.repository.UserRepository;

@Service
public class TicketServiceImpl {
	
	/*@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public boolean purchase (long userId, Booking booking) {
		Ticket ticket = new Ticket();
		ticket.setNumOfSeats(booking.getNumOfSeats());
		ticket.setPrice(booking.getPrice());
		ticket.setDepartDate(booking.getDepartureDate());
		ticket.setDepartTime(booking.getDepartureTrip().get(0).getDepartureTime());
		ticket.
		
		
		
	}
	
	public void ticketCancel (long userId, Booking booking) {
		
	}*/
	
}
