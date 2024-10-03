package com.app.train.model.trash.EmailCompartiment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


public class EmailSenderService {
     MailConfig mailConfig = new MailConfig();
    private JavaMailSender mailSender = mailConfig.javaMailSender();

    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("trenmoldavskii@gmail.com");
        message.setTo("trenmoldavskii@gmail.com");
        message.setText("1st mail");
        message.setSubject("Hello World");
        mailSender.send(message);
        System.out.println("Mail Sent...");
    }
}
