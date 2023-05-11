package com.member.api.sample;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.api.sample.entity.RedisEntity;
import com.member.api.sample.service.RedisService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "샘플", description = "샘플 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/sample/public")
public class RedisController {
	private final RedisService sampleService;

	/*
	 * redis 저장
	 */
	@PutMapping("/redis")
	public ResponseEntity<RedisEntity> put(@RequestBody RedisEntity param) {
		return ResponseEntity.ok(sampleService.put(param));
	}

	/*
	 * redis 조회
	 */
	@GetMapping("/redis/{id}")
	public ResponseEntity<RedisEntity> get(@PathVariable String id) {
		return ResponseEntity.ok(sampleService.get(id));
	}

	/*
	 * redis Count 조회
	 */
	@GetMapping("/redis/count")
	public ResponseEntity<Long> getCount() {
		return ResponseEntity.ok(sampleService.getCount());
	}

	/*
	 * redis 전체 조회
	 */
	@GetMapping("/redis")
	public ResponseEntity<List<RedisEntity>> getAll() {
		return ResponseEntity.ok(sampleService.getAll());
	}

	/*
	 * redis 삭제 - Entity
	 */
	@DeleteMapping("/redis")
	public ResponseEntity<Void> delete(@RequestBody RedisEntity param) {
		sampleService.delete(param);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*
	 * redis 삭제 - id
	 */
	@DeleteMapping("/redis/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		sampleService.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
