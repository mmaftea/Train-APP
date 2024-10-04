package com.app.train.dao.interfaces;

import com.app.train.model.entity.TrainLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainLineRepository extends JpaRepository<TrainLine, Integer> {
}