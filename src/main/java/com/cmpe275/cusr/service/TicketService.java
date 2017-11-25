package com.cmpe275.cusr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.repository.TicketRepository;
import com.cmpe275.cusr.repository.TrainRepository;
import com.cmpe275.cusr.repository.UserRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
}
