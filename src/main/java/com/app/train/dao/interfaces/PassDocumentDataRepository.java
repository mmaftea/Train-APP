package com.app.train.dao.interfaces;

import com.app.train.model.entity.PassDocumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassDocumentDataRepository extends JpaRepository<PassDocumentData, String> {
}