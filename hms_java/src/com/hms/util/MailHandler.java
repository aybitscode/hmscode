package com.hms.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHandler {
	public static  Message connectToMail(String subject) throws MessagingException,
	AddressException {
		final String username = "aybinformationtechnologies@gmail.com";
		final String password = "aybits2343";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("aybinformationtechnologies@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,
		InternetAddress.parse("javatiddler@gmail.com"));
		message.setSubject(subject);
		return message;
	}

}
