package com.app.train.api.controllers;


import com.app.train.model.dto.SeatBooking;
import com.app.train.model.dto.TicketRequest;
import com.app.train.model.entity.BaseTicket;
import com.app.train.service.impl.SeatTicketInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/train-api/seats")
public class SeatController {

    private final SeatTicketInfoService seatTicketInfoService;

    //RENAME
    @GetMapping("/reserved")
    List<SeatBooking> Get(@RequestParam int traved_id, @RequestParam int start_index, @RequestParam int end_index) {
        List<SeatBooking> seatBookings = seatTicketInfoService.GetSeatBookings(traved_id, start_index, end_index);
        return seatBookings;
    }

    @PostMapping("/reserve")
    BaseTicket createTicket(@RequestBody TicketRequest request) {
        return seatTicketInfoService.createTicket(request);
    }

}
