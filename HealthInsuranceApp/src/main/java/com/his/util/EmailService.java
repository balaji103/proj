package com.his.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private Logger logger =LoggerFactory.getLogger(EmailService.class);
	
	public void sendEmail(String to,String from,String subject,String body) {
		logger.info("sendEmail() is loaded...");
		try {
			MimeMessage mimeMsg=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMsg, true);
			
			helper.setTo(to);
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setText(body,true);
			
			mailSender.send(mimeMsg);
			logger.info("mail send successfully...");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
