package com.app.train.model.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record EmailTicketInformation(
        String travelerEmail,
        String boardStation,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime boardDateTime,
        String endStation,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDateTime,
        int ticketID,
        int seat,
        int vagon,
        String name,
        String surname
) { }