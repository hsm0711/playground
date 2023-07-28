package com.playground.api.sample.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import com.playground.api.sample.entity.SampleUserEntity;

public interface SampleUserRepository extends CrudRepository<SampleUserEntity, String>, JpaSpecificationExecutor<SampleUserEntity> {
}
