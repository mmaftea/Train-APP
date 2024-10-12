package com.app.train.service.impl;

import com.app.train.dao.interfaces.BaseTicketRepository;
import com.app.train.model.dto.SeatBooking;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.VagonClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatTicketInfoService {
    private final BaseTicketRepository baseTicketRepository;

    public List<SeatBooking> GetSeatBookings(int traved_id, int start_index, int end_index) {
        return baseTicketRepository.findAll()
                .stream()
                .filter(temp -> temp.getTravel().getId() == traved_id)
                .filter(temp -> temp.getBoardingStationIndex() >= start_index && temp.getExitStationIndex() <= end_index)
                .map(BaseTicket -> new SeatBooking(BaseTicket.getSeatNumber(), BaseTicket.getVagonIndex()))
                .collect(Collectors.toList());
    }
}
