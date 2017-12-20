package com.cmpe275.cusr.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.Ticket;
import com.cmpe275.cusr.repository.TicketRepository;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private TicketRepository ticketRepository;

	// Send text email.
	/*@Override
	public void sendTextMail(String toEmail, String subject, String text) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(text);
		javaMailSender.send(mailMessage);
	}*/

	
	@Override
	public String bookingMailBuilder(Booking booking, String template, String msg) {
		Context context = new Context();
		context.setVariable("message", msg);
		context.setVariable("numOfSeats", booking.getNumOfSeats());
		context.setVariable("passenger", booking.getPassenger());
		context.setVariable("totalPrice", booking.getPrice() + 1);
		context.setVariable("departureDate", booking.getDepartureDate());
		context.setVariable("departureTrip", booking.getDepartureTrip());
		String returnDate = booking.getReturnDate();
		String isRound = (returnDate != null && !returnDate.isEmpty()) ? "Y" : "N";
		context.setVariable("round", isRound);
		if (isRound.equals("Y")) {
			context.setVariable("returnDate", returnDate);
			context.setVariable("returnTrip", booking.getReturnTrip());
		}
		return templateEngine.process(template, context);
	}
	
	@Override
	public String ticketMailBuilder(long ticketId, String template, String msg) {
		Ticket ticket = ticketRepository.findOne(ticketId);
		Context context = new Context();
		context.setVariable("message", msg);
		context.setVariable("numOfSeats", ticket.getNumOfSeats());
		context.setVariable("passenger", ticket.getPassenger());
		context.setVariable("totalPrice", ticket.getPrice());
		context.setVariable("departureDate", ticket.getDepartDate());
		context.setVariable("departureTime", ticket.getDepartSegment1DepartTime());
		context.setVariable("departureStation", ticket.getDepartStation());
		context.setVariable("arrivalStation", ticket.getArrivalStation());
		String returnDate = ticket.getReturnDate();
		String isRound = (returnDate != null && !returnDate.isEmpty()) ? "Y" : "N";
		context.setVariable("round", isRound);
		if (isRound.equals("Y")) {
			context.setVariable("returnDate", returnDate);
			context.setVariable("returnTime", ticket.getReturnSegment1DepartTime());
		}
		return templateEngine.process(template, context);
	}
	
	// Send HTML email.
	@Override
	public void sendMail(String toEmail, String subject, String content) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			helper.setText(content, true);
			helper.setTo(toEmail);
			helper.setSubject(subject);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}
