package com.app.train.dao.interfaces;

import com.app.train.model.entity.TrainLoadedVagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainLoadedVagonRepository extends JpaRepository<TrainLoadedVagon, Integer>{
}