package com.app.train.api.controllers;

import com.app.train.model.entity.Station;
import com.app.train.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/train-api/stations")
public class StationController {

    private final StationService service;

    @GetMapping
    public List<Station> getAllApartments() {
        return service.getAllStations();
    }

    @GetMapping("/{id}")
    public Station getApartmentById(@PathVariable Integer id) {
        return service.getStationById(id);
    }

    @PostMapping
    public Station saveApartment(@RequestBody @Valid Station requestDto) {
        log.info("{}", requestDto);
        return service.addNewStation(requestDto);
    }

    @PutMapping("/{id}")
    public Station updateApartment(@PathVariable Integer id,
                                   @RequestBody @Valid Station stationDetails) {
        log.info("{}", stationDetails);
        return service.updateStation(id, stationDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Integer id) {
        service.deleteStation(id);
    }


}
