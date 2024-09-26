package com.app.train.dao.interfaces;

import com.app.train.model.entity.VagonClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagonClassRepository extends JpaRepository<VagonClass, String>{
}