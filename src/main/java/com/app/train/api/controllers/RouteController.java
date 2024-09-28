package com.app.train.api.controllers;

import com.app.train.model.entity.Route;
import com.app.train.service.impl.RouteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/train-api/routes")
public class RouteController {

    private final RouteServiceImpl service;

    @GetMapping
    public List<List<String>> getAllRoutes(@RequestParam Integer startStationId, @RequestParam Integer endStationId) {
        return service.getAllRoutesThroughPoints(startStationId, endStationId);
    }

    @GetMapping("/fastest")
    public List<Route> getFastestRoute(@RequestParam Integer startStationId, @RequestParam Integer endStationId) {
        return service.findFastestRoute(startStationId, endStationId);
    }
}
