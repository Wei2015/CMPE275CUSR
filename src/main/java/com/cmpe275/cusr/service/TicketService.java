package com.cmpe275.cusr.service;


import org.springframework.http.ResponseEntity;

import com.cmpe275.cusr.model.Ticket;

public interface TicketService {
	public ResponseEntity<Ticket> purchase (SearchContent searchRes);
	public void cancel(Ticket ticket);
}
