package com.proyecto.apartahotel.sispart.email.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements IEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String email;

	@Override
	public void sendEmail(String emailTo, String body) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messemail = new MimeMessageHelper(message, true);
			
			messemail.setFrom(email);
			messemail.setTo(emailTo);
			messemail.setSubject("SEGURIDAD:Credenciales de empleado para la plataforma SISPART.");
			messemail.setText("Las credenciales para su ingreso a la plataforma SISPART como empleado son:\r\n" + body
					+ "\r\nIMPORTANTE: Este correo electrónico es confidencial y para uso exclusivo de la(s)\r\n"
					+ "persona(s) a quien(es) se dirige. Si el lector de esta transmisión\r\n"
					+ "electrónica no es el destinatario, se le notifica que cualquier distribución\r\n"
					+ "o copia de la misma está estrictamente prohibida.");

			javaMailSender.send(message);
		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}
}
