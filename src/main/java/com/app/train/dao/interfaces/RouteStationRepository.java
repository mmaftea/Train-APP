package com.app.train.dao.interfaces;

import com.app.train.model.entity.Route;
import com.app.train.model.entity.RouteStation;
import com.app.train.model.entity.Station;
import com.app.train.model.entity.TrainLine;
import com.app.train.model.entity.TrainLineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteStationRepository extends JpaRepository<RouteStation, Integer> {

    @Query("SELECT r FROM RouteStation r WHERE r.lineElement.station.id = :station AND r.route = :route")
    Optional<RouteStation> findByStationAndRoute(@Param("station") Integer station, @Param("route") Route route);

    @Query("select r.stationIndex from RouteStation r where r.route.id = ?1 and r.lineElement.station.id = ?2")
    Optional<Integer> getIndexByStation(Integer id, Integer id1);

    @Query("select r from RouteStation r where r.route = ?1 and r.stationIndex = ?2")
    Optional<RouteStation> findByIndexAndRoute(Route route, Integer stationIndex);

    @Query("SELECT DISTINCT r.lineElement.trainLine FROM RouteStation r WHERE r.stationIndex >= :start and r.stationIndex <=:end and r.route = :route")
    List<TrainLine> getTrainLinesInBetweenStations(@Param("start") Integer startIndex, @Param("end") Integer endIndex, @Param("route") Route route);

    @Query("SELECT new com.app.train.model.entity.TrainLineInfo(r.lineElement.trainLine, COUNT(r)) " +
            "FROM RouteStation r " +
            "WHERE r.stationIndex >= :start AND r.stationIndex <= :end AND r.route = :route " +
            "GROUP BY r.lineElement.trainLine")
    List<TrainLineInfo> getTrainLinesInBetweenStationsWithCount(@Param("start") Integer startIndex, @Param("end") Integer endIndex, @Param("route") Route route);


    Optional<RouteStation> findByRouteAndStationIndex(Route route, int i);

    @Query("SELECT r.route.id, r.lineElement.station.id FROM RouteStation r " +
            "WHERE r.stationIndex = (SELECT MAX(r2.stationIndex) FROM RouteStation r2 WHERE r2.route.id = r.route.id) " +
            "GROUP BY r.route.id")
    List<Object[]> findLastStationIdsByRoutes();

    @Query("select MAX(r.stationIndex) from RouteStation r where r.route.id = ?1")
    Integer findLastIndexByRoute(Integer id);


}