package com.app.train.dao.interfaces;

import com.app.train.model.entity.Route;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteStationRepository extends JpaRepository<RouteStation, Integer> {
    List<RouteStation> findByStation(Station station);

    RouteStation findTopByRouteAndStationIndexLessThanOrderByStationIndexDesc(Route route, int stationIndex);

    List<RouteStation> findByRouteAndStationIndexGreaterThanOrderByStationIndexAsc(Route route, int stationIndex);

}