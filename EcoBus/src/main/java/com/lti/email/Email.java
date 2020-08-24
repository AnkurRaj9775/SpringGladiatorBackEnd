package com.lti.email;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;


@Component
public class Email {

	/*
	 * @Autowired private MailSender mailSender;
	 */
	
	public void registerEmail(String email,String name) {
		
		MailSender mailSender =new MailSender() {
			
			@Override
			public void send(SimpleMailMessage... simpleMessages) throws MailException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void send(SimpleMailMessage simpleMessage) throws MailException {
				// TODO Auto-generated method stub
				
			}
		};
		System.out.println(email);
		System.out.println(name);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ecobusserviceddn@gmail.com");
		message.setTo(email);
		message.setSubject("Welcome to your new ecoBus Account");
		message.setText("Hi"+" " +name+ ".Thanks for Creating account.Looking Forword to your Great Journeys ahead!!!. ");
		System.out.println(message.toString());
		mailSender.send(message);
		
	}
	
	
}
