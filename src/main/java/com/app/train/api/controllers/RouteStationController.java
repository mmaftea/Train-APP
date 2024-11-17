package com.app.train.api.controllers;

import com.app.train.model.dto.TicketMetaData;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.Station;
import com.app.train.service.impl.RouteStationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/train-api/rs")
public class RouteStationController {
    private final RouteStationServiceImpl service;

    @GetMapping("/{index}")
    public Station getStationByIndexOnRoute(@PathVariable int index,
                                            @RequestParam Integer routeId) {
        return service.getStationFromIndex(index, routeId);
    }

    @PostMapping("/squash")
    public List<TicketMetaData> formatMetadata(@RequestBody List<TravelResult> resultList, @RequestParam Integer startId) {
        return service.squashResults(resultList, startId);
    }

    @PostMapping("/meta")
    public List<Station> stationListInBetween(@RequestBody TicketMetaData metaData) {
        return service.getStationInBetween(metaData);
    }

}
