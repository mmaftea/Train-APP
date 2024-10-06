package com.app.train.dao.interfaces;

import com.app.train.model.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {
    @Query("select t from Travel t where t.routeId = ?1 and t.startDateTime = ?2")
    Optional<Travel> findByDateAndRoute(Integer routeId, LocalDateTime startDateTime);


}