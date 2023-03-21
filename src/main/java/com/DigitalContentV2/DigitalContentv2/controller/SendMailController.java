package com.DigitalContentV2.DigitalContentv2.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DigitalContentV2.DigitalContentv2.facadeImp.SendMailService;
import com.DigitalContentV2.DigitalContentv2.facadeImp.Usuariodao;
import com.DigitalContentV2.DigitalContentv2.modelo.Mail;

@RequestMapping("/mail")
@Controller
public class SendMailController {

	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private Usuariodao usuarioDao;
	
	@GetMapping("/view")
	public String mail() {
	
		
		return "mail_view";
	}
	
	@GetMapping("/view-mails")
	public String mails() {
		
		return "sendsmails";
	}
	
	@GetMapping("/correos")
	public String correos(String correos,Model model) {
		String[] listaCorreos = usuarioDao.listarCorreos();
		model.addAttribute("listaCorreos", listaCorreos);
		return "mail_view";
	}
	
	
	 @PostMapping("/sendMail")
	    public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("subject") String subject, @RequestParam("body") String body){

	        String message = body +"\n\n Datos de contacto: " + "\nNombre: " + name + "\nCorreo: " + mail;
	        sendMailService.sendMail("digitialcontent@gmail.com",mail,subject,message);

	        return "mail_view";
	    }
	 
	 @PostMapping("/sendMailWithTemplate")
	 public String sendMailTemplate(@RequestParam("subject") String subject, Mail mail) throws MessagingException, IOException {
		 
		 	String[] listaCorreos = usuarioDao.listarCorreos();
		 
		 	for (int i = 0; i < listaCorreos.length; i++) {
		 		mail.setFrom("digitialcontent@gmail.com");
				 mail.setMailTo(listaCorreos[i]);
				 mail.setSubject(subject);
				 
				 sendMailService.sendEmailTemplate(mail);
			}
			 

		 return "sendsmails";
		 
	 }
	 
	 
}
