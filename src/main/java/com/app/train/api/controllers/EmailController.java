package com.app.train.api.controllers;

import com.app.train.model.records.EmailTicketInformation;
import com.app.train.model.trash.EmailCompartiment.EmailSenderService;
import com.app.train.model.trash.EmailCompartiment.PDFService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/train-api/email")
public class EmailController {

    private final EmailSenderService emailService;

    @PostMapping("/send")
    void sendEmail(@RequestParam String reciver, @RequestParam String message) {
        emailService.sendSimpleEmail(reciver, message);
    }

    @PostMapping(value = "/sendFile")
    void sendEmailWithAttachment(@RequestBody EmailTicketInformation info) {

        Map<String, String> placeholders = new HashMap<>();

        placeholders.put("ticket_id", "" + info.ticketID());
        placeholders.put("passsager_name", info.surname() + info.name());
        placeholders.put("travel_id", "" + info.travel_id());
        placeholders.put("train_id", "" + info.train_id());
        placeholders.put("seat_number", "" + info.seat());
        placeholders.put("vagon_number", "" + info.vagon());
        placeholders.put("start_city", info.boardStation());
        placeholders.put("end_city", "" + info.boardDateTime());
        placeholders.put("boarding date time", info.endStation());
        placeholders.put("arrival date time", "" + info.endDateTime());
        placeholders.put("ticket_class", info.ticket_class());
        placeholders.put("distance", info.distance() + " km");
        placeholders.put("person_type", info.person_type());
        placeholders.put("price", "" + info.price());


        // CREATE A PDF FILE
        String outputPdfPath = "src/main/java/com/app/train/model/trash/EmailCompartiment/ticketTemplates/ticketsPdf/" + info.ticketID() + ".pdf";
        String templatePath = "src/main/java/com/app/train/model/trash/EmailCompartiment/ticketTemplates/testTemplate.html";

        try {
            PDFService.generatePdf(templatePath, outputPdfPath, placeholders);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        // SENDING THE FILE
        emailService.sendMailWithAttachment(info.travelerEmail(), "", "TICKETS", outputPdfPath);
        //emailService.sendMailWithAttachment("1saganeanmarius@gmail.com","message body","test 01","src/main/java/com/app/train/model/trash/EmailCompartiment/ticketTemplates/ticketsPDF/Business Class Boarding Pass Ticket.pdf");
    }
}


