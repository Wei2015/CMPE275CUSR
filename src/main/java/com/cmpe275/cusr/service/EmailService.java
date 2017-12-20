package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.Booking;

public interface EmailService {
	
	//public void sendTextMail(String toEmail, String subject, String text);
	
	public String bookingMailBuilder(Booking booking, String template, String msg);
	
	public String ticketMailBuilder(long ticketId, String template, String msg);
	
	public void sendMail(String toEmail, String subject, String content);
	
}
