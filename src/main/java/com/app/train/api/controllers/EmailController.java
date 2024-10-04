package com.app.train.api.controllers;

import com.app.train.model.records.EmailTicketInformation;
import com.app.train.model.trash.EmailCompartiment.EmailSenderService;
import com.app.train.model.trash.EmailCompartiment.PDFService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
    void sendEmail(@RequestParam String reciver,@RequestParam String message) {
        emailService.sendSimpleEmail(reciver, message);
    }
    @PostMapping(value = "/sendFile", consumes = "application/json")
    void sendEmailWithAttachment(@RequestBody EmailTicketInformation info){

        Map<String, String> placeholders = new HashMap<>();

        placeholders.put("ticket_id", "" + info.ticketID());
        placeholders.put("name", info.name());
        placeholders.put("surname", info.surname());
        placeholders.put("seat", "" + info.seat());
        placeholders.put("vagon", "" + info.vagon());
        placeholders.put("boarding_station", info.boardStation());
        placeholders.put("boarding_station_date_time", "" + info.boardDateTime());
        placeholders.put("exit_station", info.endStation());
        placeholders.put("exit_station_date_time", info.endStation());

        // CREATE A PDF FILE
        String outputPdfPath = "src/main/java/com/app/train/model/trash/EmailCompartiment/ticketTemplates/ticketsPdf/" + info.ticketID() + ".pdf";
        String templatePath = "src/main/java/com/app/train/model/trash/EmailCompartiment/ticketTemplates/testTemplate.html";

        try
        {
            PDFService.generatePdf(templatePath, outputPdfPath, placeholders);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        // SENDING THE FILE
        emailService.sendMailWithAttachment(info.travelerEmail(),"","TICKETS",outputPdfPath);
        //emailService.sendMailWithAttachment("1saganeanmarius@gmail.com","message body","test 01","src/main/java/com/app/train/model/trash/EmailCompartiment/ticketTemplates/ticketsPDF/Business Class Boarding Pass Ticket.pdf");
    }

}


