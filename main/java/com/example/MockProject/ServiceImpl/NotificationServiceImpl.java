package com.example.MockProject.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl{
	
private static JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationServiceImpl(JavaMailSender javaMailSender) {
	NotificationServiceImpl.javaMailSender = javaMailSender;
}
	
	//public void sendEmail()throws MailException
	public static void sendEmail(String mailId, String message, String subject)throws MailException  {
	//send email
	SimpleMailMessage mail = new SimpleMailMessage();

	//mail.setTo(user.getEmail());
	mail.setTo(mailId);
	mail.setFrom("mockinterviewschedular@gmail.com");
	mail.setSubject(subject);
	mail.setText(message);
	javaMailSender.send(mail);
	
}


}
