package com.cmpe275.cusr.service;

import java.io.IOException;
import java.io.InputStream;

public interface EmailService {

	public void sendMail(String toEmail, String subject, String message) throws Exception;

	public String getURLSource(String url) throws IOException;

	public String toString(InputStream inputStream) throws IOException;
}
