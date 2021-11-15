package com.libreriaWebApp.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServicio {
	
	@Autowired
	private JavaMailSender mailSender;
	@Async
	public void sendSimpleMail(String toEmail,
								String body,
								String subject) {
		SimpleMailMessage mail = new  SimpleMailMessage();
		mail.setFrom("31gonzalovega@gmail.com");
		mail.setTo(toEmail);
		mail.setText(body);
		mail.setSubject(subject);
		
		mailSender.send(mail);
		System.out.println("Email enviado..");
	}

}
