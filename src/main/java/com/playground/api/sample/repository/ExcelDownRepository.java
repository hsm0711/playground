package com.playground.api.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.playground.api.sample.entity.ExcelDownEntity;

@Repository
public interface ExcelDownRepository extends JpaRepository<ExcelDownEntity, String> {
}
