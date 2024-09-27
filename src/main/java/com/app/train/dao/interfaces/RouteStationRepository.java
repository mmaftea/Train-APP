package com.app.train.dao.interfaces;

import com.app.train.model.entity.RouteStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteStationRepository extends JpaRepository<RouteStation, Integer> {
    @Query("SELECT rs FROM RouteStation rs WHERE rs.route.id = :routeId")
    List<RouteStation> findAllByRoute(@Param("routeId") Integer routeId);
}