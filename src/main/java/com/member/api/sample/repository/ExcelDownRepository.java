package com.member.api.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.member.api.sample.entity.ExcelDownEntity;

@Repository
public interface ExcelDownRepository extends JpaRepository<ExcelDownEntity, String>{
}
