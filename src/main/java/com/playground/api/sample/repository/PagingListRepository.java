package com.playground.api.sample.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.playground.api.sample.entity.PagingEntity;

public interface PagingListRepository extends CrudRepository<PagingEntity, String> {
  List<PagingEntity> findAll(Pageable pageable);
}
