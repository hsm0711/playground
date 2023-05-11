package com.member.api.sample.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.member.api.sample.entity.RedisEntity;

public interface RedisRepository extends CrudRepository<RedisEntity, String> {
	@Override
	List<RedisEntity> findAll();
}
