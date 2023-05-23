package com.member.api.sample.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.member.api.sample.entity.RedisRepositoryEntity;

public interface RedisRepository extends CrudRepository<RedisRepositoryEntity, String> {
  @Override
  List<RedisRepositoryEntity> findAll();
}
