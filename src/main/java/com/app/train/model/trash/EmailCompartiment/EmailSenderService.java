package com.app.train.model.trash.EmailCompartiment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendSimpleEmail(String reciver, String message) {
        try {
            log.info("send a simple mail without attachment file");
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(reciver);
            mailMessage.setText(message);
            mailMessage.setSubject("TEST");

            mailSender.send(mailMessage);
        } catch (Exception exception) {
            log.error("error while sending the mail");
            throw new RuntimeException("error while sending the mail : "
                    + exception.getMessage());
        }
    }
}
