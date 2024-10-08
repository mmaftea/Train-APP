package com.app.train.dao.interfaces;

import com.app.train.model.entity.Vagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagonRepository extends JpaRepository<Vagon, Integer> {
}