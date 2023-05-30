package com.member.api.sample.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import com.member.api.sample.entity.PagingEntity;

public interface PagingSliceRepository extends CrudRepository<PagingEntity, String> {
  Slice<PagingEntity> findAll(Pageable pageable);
}
