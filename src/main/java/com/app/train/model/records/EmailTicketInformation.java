package com.app.train.model.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record EmailTicketInformation(
        String travelerEmail,
        String boardStation,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime boardDateTime,
        String endStation,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateTime,
        int ticketID,
        int seat,
        int vagon,
        String person_type,
        int train_id,
        String ticket_class,
        float price,
        String curency
) { }