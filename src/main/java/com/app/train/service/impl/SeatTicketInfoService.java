package com.app.train.service.impl;

import com.app.train.dao.interfaces.BaseTicketRepository;
import com.app.train.model.dto.SeatBooking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatTicketInfoService {
    private final BaseTicketRepository baseTicketRepository;

    //rename
    public List<SeatBooking> GetSeatBookings(int traved_id, int start_index, int end_index) {
        return baseTicketRepository.findAll()
                .stream()
                .filter(temp -> temp.getTravel().getId() == traved_id)
                .filter(temp -> temp.getBoardingStationIndex() >= start_index && temp.getExitStationIndex() <= end_index)
                .map(ticket -> new SeatBooking(ticket.getSeatNumber(), ticket.getVagonIndex()))
                .collect(Collectors.toList());
    }
}
