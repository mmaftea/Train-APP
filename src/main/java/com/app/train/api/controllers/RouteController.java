package com.app.train.api.controllers;

import com.app.train.model.entity.Route;
import com.app.train.model.entity.Station;
import com.app.train.service.impl.RouteServiceImpl;
import com.app.train.service.impl.RouteStationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/train-api/routes")
public class RouteController {

    private final RouteServiceImpl service;
    private final RouteStationServiceImpl routeStationService;

    @GetMapping
    public ResponseEntity<List<List<String>>> getAllRoutes(@RequestParam Integer startStationId, @RequestParam Integer endStationId) {
        return ok(service.getAllRoutesThroughPoints(startStationId, endStationId));
    }

    @GetMapping("/inflex")
    public List<Station> getFastestRoute() {
        return routeStationService.findInflectionPoints();
    }
}
