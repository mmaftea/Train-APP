package com.app.train.service.impl;

import com.app.train.dao.interfaces.StationRepository;
import com.app.train.model.entity.Station;
import com.app.train.model.exceptions.AppException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.train.model.exceptions.ExceptionResponses.STATION_NOT_FOUND;
import static java.lang.String.valueOf;
import static java.util.Comparator.comparing;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements com.app.train.service.StationService {
    private final StationRepository repository;

    @Override
    public List<Station> getAllStations() {
        return repository.findAll().stream()
                .sorted(comparing(Station::getStationName))
                .toList();
    }

    @Override
    public Station getStationById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(STATION_NOT_FOUND, valueOf(id)));
    }

    @Override
    public Station addNewStation(Station apartment) {
        return repository.save(apartment);
    }

    @Override
    @Transactional
    public Station updateStation(int id, Station stationDetails) {
        var station = getStationById(id);
        station.setId(id);
        return repository.save(station);
    }

    @Override
    public void deleteStation(int id) {
        Station apartment = getStationById(id);
        repository.delete(apartment);
    }
}
