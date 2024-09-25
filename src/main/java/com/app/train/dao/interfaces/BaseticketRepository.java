package com.app.train.dao.interfaces;

import com.app.train.model.Entities.Baseticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseticketRepository extends JpaRepository<Baseticket, Integer> {
}