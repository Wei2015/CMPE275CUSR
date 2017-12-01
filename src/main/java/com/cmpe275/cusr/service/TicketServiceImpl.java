package com.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.SearchContent;
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
	
	public ResponseEntity<Ticket> purchase (long id, OneWaySearchRes searchRes) {
		Character departureStation = searchRes.getDepartureStation();
		Character destinationStation = searchContent.getDestinationStation();
		String departureDate = searchContent.getReturnDate();
		String departureTime = searchContent.getDepartureTime();
		boolean roundTrip = searchContent.isRoundTrip();
		String returnDate = searchContent.getReturnDate();
		String returnTime = searchContent.getReturnTime();
		
		Ticket ticket = new Ticket();
	}*/
	
}
