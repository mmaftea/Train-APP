package com.app.train.api.controllers;

import com.app.train.model.dto.TravelResult;
import com.app.train.service.RaptorService;
import com.app.train.util.raptor.AllRoutesRaptorService;
import com.app.train.util.raptor.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketBookingController {

    private final AllRoutesRaptorService allRoutesRaptorService;
    private final RaptorService raptorService;

    @GetMapping("/findRoutes")
    public List<Path> findRoutes(@RequestParam int startStationId, @RequestParam int endStationId) {
        return allRoutesRaptorService.findAllRoutes(startStationId, endStationId);
    }

    @GetMapping("/findTravelsDEMO")
    public List<List<TravelResult>> findTravels(@RequestParam int startStationId, @RequestParam int endStationId, @RequestParam LocalDateTime localDateTime) {
        return raptorService.searchForTravels(startStationId, endStationId, localDateTime, 0);
    }
}
