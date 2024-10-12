package com.app.train.api.controllers;


import com.app.train.model.dto.SeatBooking;
import com.app.train.service.impl.SeatTicketInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    List<SeatBooking> Get(@RequestParam int traved_id, @RequestParam int start_index, @RequestParam int end_index){
        return seatTicketInfoService.GetSeatBookings(traved_id, start_index, end_index);
    }

}
