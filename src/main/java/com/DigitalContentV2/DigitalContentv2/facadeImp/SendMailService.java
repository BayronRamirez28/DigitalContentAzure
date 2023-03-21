package com.DigitalContentV2.DigitalContentv2.facadeImp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.DigitalContentV2.DigitalContentv2.modelo.Mail;



@Service
public class SendMailService {
	
	    @Autowired
	    private JavaMailSender emailSender;
	    
	    @Autowired
	    private SpringTemplateEngine templateEngine;
	    
	    @Autowired
	    private JavaMailSender javaMailSender;

	    public void sendMail(String from, String to, String subject, String body) {

	        SimpleMailMessage mail = new SimpleMailMessage();

	        mail.setFrom(from);
	        mail.setTo(to);
	        mail.setSubject(subject);
	        mail.setText(body);
	       
	        javaMailSender.send(mail);
	    }
	    
	    public void sendEmailTemplate(Mail mail) throws MessagingException, IOException {
	        MimeMessage message = emailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,
	                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	                StandardCharsets.UTF_8.name());
	        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));
	        Context context = new Context();
	        context.setVariables(mail.getProps());
	    
	        String html = templateEngine.process("emailTemplate", context);
	        helper.setTo(mail.getMailTo());
	        helper.setText(html, true);
	        helper.setSubject(mail.getSubject());
	        helper.setFrom(mail.getFrom());
	        emailSender.send(message);
	    }
	}
