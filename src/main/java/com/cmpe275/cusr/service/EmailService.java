package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.Booking;

public interface EmailService {
	public void sendTextMail(String toEmail, String subject, String text);
	
	public String mailBuilder(Booking booking, String template);
	
	public String ticketMailBuilder(long ticketId, String template);
	
	public void sendMail(String toEmail, String subject, String message);
	
}
