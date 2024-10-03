package com.app.train.model.trash.EmailCompartiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

public class Main {

    public static void main(String[] args) {
        EmailSenderService emailSenderService = new EmailSenderService();
        emailSenderService.sendSimpleEmail();
    }
}
