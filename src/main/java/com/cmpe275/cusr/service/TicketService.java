package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.User;

public interface TicketService {
	
	public boolean purchase(User user, Booking booking);
	public boolean cancel(long ticketId);
	public boolean timeCheck (String date, String time, int diff); 
	
}