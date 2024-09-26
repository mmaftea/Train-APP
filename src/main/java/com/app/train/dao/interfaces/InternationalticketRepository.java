package com.app.train.dao.interfaces;

import com.app.train.model.entity.InternationalTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternationalticketRepository extends JpaRepository<InternationalTicket, Integer> {
}