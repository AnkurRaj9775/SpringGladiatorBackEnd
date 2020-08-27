package com.lti.email;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.lti.dto.CustomerDetails;
import com.lti.dto.PassengerDetails;
import com.lti.dto.SeatDetails;
import com.lti.dto.TicketDetails;


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
	
	
	
	public void ticketDetailsEmail(CustomerDetails customerDetails, TicketDetails ticketDetails,int ticketId) {
		

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ecobusserviceddn@gmail.com");
		message.setTo(customerDetails.getEmail());
		message.setSubject("Welcome to your new ecoBus Account");
		message.setText(" Congratulations! You have booked a reschedulable ticket.   "
				+ ""
				+ ticketDetails.getFromCity()+ "====>>>" + ticketDetails.getToCity()+ " "
				+ " Date of Journey :" +ticketDetails.getDateOfJourney() + " "+ " "
				+ " PNR no :"+ticketId + ""
				+ " Bus Id :" + ticketDetails.getBusId()+ " "
				+ " Total Seats Booked :"+ticketDetails.getNoOfSeatsBooked() +""
				+ " Total Fare :" + ticketDetails.getTotalCost() + " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " NOTE : This operator accepts mTicket, you need not carry a print out"+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ " "
				+ "Thank You For Booking With EcoBus  !! "
				
				
				);
		System.out.println(message.toString()+"" + "testing");
		mailSender.send(message);
		System.out.println("Mail sent");
		
	}
	
}
