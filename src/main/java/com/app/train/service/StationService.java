package com.app.train.service;

import com.app.train.model.entity.Station;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StationService {
    List<Station> getAllStations();

    Station getStationById(int id);

    Station addNewStation(Station apartment);

    @Transactional
    Station updateStation(int id, Station stationDetails);

    void deleteStation(int id);
}
