package com.app.train.service.impl;

import com.app.train.dao.interfaces.RouteStationRepository;
import com.app.train.dao.interfaces.TravelRepository;
import com.app.train.model.dto.TravelResult;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.Travel;
import com.app.train.service.RaptorService;
import com.app.train.util.raptor.AllRoutesRaptorService;
import com.app.train.util.raptor.Connection;
import com.app.train.util.raptor.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
@RequiredArgsConstructor
public class RaptorServiceImpl implements RaptorService {

    private final AllRoutesRaptorService allRoutes;
    private final TravelRepository travelRepository;
    private final RouteStationRepository routeStationRepository;
    private final UtilityServiceImpl utilityService;

    private Integer startIndex = 1;
    private Integer startStation = 1;


    @Override
    public List<List<TravelResult>> searchForTravels(Integer start, Integer end, LocalDate date, int numOfTickets) {
        var paths = allRoutes.findAllRoutes(start, end);
        paths = paths.parallelStream().distinct().toList();


        Map<Integer, Connection> lastConnectionByRoute = paths.stream()
                .sorted(comparing(Path::getTransfers).reversed())
                .flatMap(path -> path.getConnections().stream())
                .collect(Collectors.toMap(
                        Connection::getRouteId,
                        connection -> connection,
                        (existing, replacement) -> replacement
                ));


        List<List<Travel>> travelsToTake = paths.parallelStream()
                .map(path -> {
                    List<List<Travel>> routeTravels = path.getConnections().stream()
                            .map(Connection::getRouteId)
                            .distinct()
                            .map(routeId -> travelRepository.findByDateAndRoute(routeId, date))
                            .toList();

                    return cartesianProduct(routeTravels);
                })
                .flatMap(Collection::stream)
                .toList();


        travelsToTake = travelsToTake.parallelStream()
                .filter(travelList -> travelList.size() == 1 || consecutiveTimes(travelList))
                .collect(Collectors.toList());

        List<List<TravelResult>> mapped =
                mapToResult(travelsToTake, start, end, lastConnectionByRoute);

        return mapped.parallelStream().distinct().toList();
    }

    private List<List<TravelResult>> mapToResult(List<List<Travel>> travels, Integer start, Integer end, Map<Integer, Connection> lastConnectionByRoute) {
        List<List<TravelResult>> result = new ArrayList<>();
        for (List<Travel> t : travels) {
            startIndex = -start;
            startStation = start;
            var list = t.stream()
                    .map(travel -> this.resultBuilder(travel, lastConnectionByRoute))
                    .toList();
            TravelResult travelResult = list.get(list.size() - 1);
            if (!list.contains(null) && travelResult.getEndId() != travelResult.getStartId())
                result.add(list);
        }
        startIndex = -1;

        return result;
    }

    private <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
        List<List<T>> result = new ArrayList<>();
        cartesianProductProxy(lists, result, 0, new ArrayList<>());
        return result;
    }

    private <T> void cartesianProductProxy(List<List<T>> lists, List<List<T>> result, int depth, List<T> current) {
        if (depth == lists.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (T element : lists.get(depth)) {
            current.add(element);
            cartesianProductProxy(lists, result, depth + 1, current);
            current.remove(current.size() - 1);
        }
    }

    private TravelResult resultBuilder(Travel t, Map<Integer, Connection> lastConnectionByRoute) {
        if (startIndex < 0) {
            try {
                startIndex = routeStationRepository.getIndexByStation(t.getRouteId(), -startIndex).orElseThrow();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            var wtf = new RouteStation();
            wtf.setStationIndex(9999);
            startIndex = routeStationRepository.findByStationAndRoute(startStation, t.getRoute()).orElse(wtf).getStationIndex();
        }
        if (startIndex == 9999) return null;
        var temp = getStation(t, lastConnectionByRoute);
        if (temp.getStationIndex() == 9999) return null;
        var lastIndex = temp.getStationIndex();

        TravelResult build = TravelResult.builder()
                .travel(t)
                .startId(startIndex)
                .endId(lastIndex)
                .build();
        startIndex = lastIndex;
        startStation = temp.getLineElement().getStation().getId();
        return build;
    }

    private RouteStation getStation(Travel t, Map<Integer, Connection> lastConnectionByRoute) {
        var wtf = new RouteStation();
        wtf.setStationIndex(9999);
        return routeStationRepository
                .findByStationAndRoute(lastConnectionByRoute.get(t.getRouteId()).getDestinationStopId(), t.getRoute()).orElse(wtf);
    }

    private boolean consecutiveTimes(List<Travel> travels) {
        for (int i = 0; i < travels.size() - 1; i++) {
            Travel current = travels.get(i);
            Travel next = travels.get(i + 1);

            var ending = routeStationRepository.findLastIndexByRoute(current.getRouteId());

            TravelResult result = TravelResult.builder()
                    .travel(current)
                    .startId(0)
                    .endId(ending)
                    .build();

            LocalDateTime currentEndTime = current.getStartDateTime()
                    .plusMinutes(utilityService.calculateDistanceAndDuration(result).getMinutes());
            LocalDateTime nextStartTime = next.getStartDateTime();


            if (currentEndTime.isAfter(nextStartTime)
                    || Duration.between(currentEndTime, nextStartTime).toMinutes() > 600
                    || Duration.between(currentEndTime, nextStartTime).toMinutes() < 0
            ) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<TravelResult> fastestTravelPossible(Integer start, Integer end, LocalDateTime dateTime, int numOfTickets) {
        return List.of();
    }
}
