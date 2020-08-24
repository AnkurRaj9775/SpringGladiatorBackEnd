package com.lti.email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class Email {

	
	 @Autowired
	 private JavaMailSender mailSender;
	 
	
	public void registerEmail(String email,String name) {
		
	
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ecobusserviceddn@gmail.com");
		message.setTo(email);
		message.setSubject("Welcome to your new ecoBus Account");
		message.setText("Hi"+" " + name + ".Thanks for Creating account.Looking Forword to your Great Journeys ahead!!!. ");
		System.out.println(message.toString()+"" + "testing");
		mailSender.send(message);
		System.out.println("Mail sent");
	}
	
	
}
