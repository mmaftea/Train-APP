package com.app.train.api.controllers;

import com.app.train.model.trash.EmailCompartiment.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/train-api/email")
public class EmailController {

    private final EmailSenderService emailService;

    @PostMapping("/send")
    void sendEmail(@RequestParam String reciver,@RequestParam String message) {
        emailService.sendSimpleEmail(reciver, message);
    }
}
