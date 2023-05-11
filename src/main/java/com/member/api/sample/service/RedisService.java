package com.member.api.sample.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.member.api.sample.entity.RedisEntity;
import com.member.api.sample.repository.RedisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
	private final RedisRepository repo;

	public RedisEntity put(RedisEntity param) {
		param.setCreatedAt(LocalDateTime.now());

		return repo.save(param);
	}

	public RedisEntity get(String id) {
		return repo.findById(id).orElseGet(RedisEntity::new);
	}

	public long getCount() {
		return repo.count();
	}

	public List<RedisEntity> getAll() {
		return repo.findAll();
	}

	public void delete(RedisEntity param) {
		repo.delete(param);
	}

	public void deleteById(String id) {
		repo.deleteById(id);
	}
}
