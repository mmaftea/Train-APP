package com.app.train.api.controllers;

import com.app.train.util.raptor.AllRoutesRaptorService;
import com.app.train.util.raptor.Path;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketBookingController {

    private final AllRoutesRaptorService raptorService;

    public TicketBookingController(AllRoutesRaptorService raptorService) {
        this.raptorService = raptorService;
    }

    @GetMapping("/findRoutes")
    public List<Path> findRoutes(@RequestParam int startStationId, @RequestParam int endStationId) {
        return raptorService.findAllRoutes(startStationId, endStationId);
    }
}
