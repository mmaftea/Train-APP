package com.app.train.model.trash.EmailCompartiment;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;
    public void sendSimpleEmail(String reciver, String message) {
        try
        {
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
    public void sendMailWithAttachment(String toEmail, String body, String subject, String attachment) {
        try {
            log.info("send a simple mail with attachment file");
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("trenmoldavskii@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            mailSender.send(mimeMessage);
            log.info("Mail with attachment sent successfully..");
            }catch (Exception exception) {
                log.error("error while sending the mail");
                throw new RuntimeException("error while sending the mail : "
                        + exception.getMessage());
            }

    }


}
