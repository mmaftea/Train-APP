package com.app.train.dao.interfaces;

import com.app.train.model.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer>{
}