package com.app.train.dao.interfaces;

import com.app.train.model.Entities.Routestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutestationRepository extends JpaRepository<Routestation, Integer> {
}