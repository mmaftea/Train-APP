package com.app.train.util.raptor;

import com.app.train.dao.interfaces.RouteStationRepository;
import com.app.train.dao.interfaces.StationRepository;
import com.app.train.dao.interfaces.TrainLineElementRepository;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.Station;
import com.app.train.model.entity.Travel;
import com.app.train.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RaptorEntityMapper {

    private final StationRepository stationRepository;
    private final RouteStationRepository routeStationRepository;
    private final TrainLineElementRepository trainLineElementRepository;
    private final UtilityService utilityService;

    public Map<Integer, Stop> loadStops() {
        Map<Integer, Stop> stopsMap = new HashMap<>();

        List<Station> stations = stationRepository.findAll();
        for (Station station : stations) {
            Stop stop = new Stop(station.getId(), station.getStationName());
            stopsMap.put(station.getId(), stop);
        }

        List<RouteStation> routeStations = routeStationRepository.findAll();
        for (RouteStation routeStation : routeStations) {
            int currentStationId = routeStation.getLineElement().getStation().getId();
            int nextStationId = getNextStationId(routeStation);

            if (nextStationId != -1) {
                int routeId = routeStation.getRoute().getId();
                var mock = createMockTravel(routeStation, currentStationId, nextStationId);
                long duration = utilityService.calculateDistanceAndDuration(mock).getMinutes();

                Stop stop = stopsMap.get(currentStationId);
                Connection connection = new Connection(nextStationId, routeId, duration);

                stop.addConnection(connection);
            }
        }

        return stopsMap;
    }

    private static TravelResult createMockTravel(RouteStation routeStation, int currentStationId, int nextStationId) {
        var tr = new Travel();
        tr.setRoute(routeStation.getRoute());
        return TravelResult.builder()
                .travel(tr)
                .startId(currentStationId)
                .endId(nextStationId)
                .build();
    }

    private int getNextStationId(RouteStation currentRouteStation) {
        Optional<RouteStation> nextRouteStation = routeStationRepository.findByRouteAndStationIndex(
                currentRouteStation.getRoute(), currentRouteStation.getStationIndex() + 1
        );
        if (nextRouteStation.isPresent()) {
            return nextRouteStation.get().getLineElement().getStation().getId();
        } else return -1;
    }
}
