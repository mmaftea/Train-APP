package com.app.train.dao.interfaces;

import com.app.train.model.entity.BaseTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseTicketRepository extends JpaRepository<BaseTicket, Integer> {
}