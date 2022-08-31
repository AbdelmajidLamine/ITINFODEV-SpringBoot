package com.demo.web;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.base.codec.CharEncoding;

@Component
public class pin {

	@Autowired
	private JavaMailSender mailSender;

	public Boolean Pin(String loging, String password) throws MessagingException {
		// SimpleMailMessage mailMessage = new SimpleMailMessage();
		MimeMessage mailMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, CharEncoding.UTF_8);

		String mailSubject = "Réinitialisez votre mot de passe boucherie2002-orleanslasource";
		String mailContent = "<h2 style=\"text-transform: uppercase;\"><span style= \"color:#F39C12;\">M</span>boucherie2002-orleanslasource  </h2>\n";
		mailContent += "<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">Nous avos entendu dire que vous avez perdu votre mot de passe boucherie2002 .Désolé pour ça! </p>"
				+ "<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">" + loging
				+ "</h1>"
				+ "<p style= \"font-weight: 400; color: #111;font-size: 21px;font-family:Trebuchet MS, sans-serif;\">Vous pouvez utiliser le code pin suivant pour réintialiser votre mot de pass   </p>"
				+ "<h1 style=\"color:#F39C12; text-align: center; font-weight: 600; font-size: 22px;\">" + password
				+ "</h1>";

		mailContent += "<span style= \"font-weight: 300; color: #111;font-size: 19px;font-family:Trebuchet MS, sans-serif;\">si vous avez un problème vous pouvez contacter l'email  boucherie2002 dans votre Service</a>"
				+ "</span>";
		helper.setTo(loging);
		helper.setFrom("mathimatique2019@gmail.com");

		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);

		// File file = new File("src/main/resources/static/FilesUploaded/logo.jpg");

		// helper.addAttachment(file.getName(), file);

		mailSender.send(mailMessage);
		return true;
	}

}
