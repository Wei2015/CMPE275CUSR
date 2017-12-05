package com.cmpe275.cusr.service;

public interface EmailService {
	public void sendMail(String toEmail, String subject, String text);
}
