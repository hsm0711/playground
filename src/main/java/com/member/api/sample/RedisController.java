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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Redis 샘플 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/public/sample/redis")
public class RedisController {
	private final RedisService sampleService;

	/*
	 * redis 저장
	 */
	@ApiOperation(value = "redis 저장", notes = "redis 저장")
	@PutMapping
	public ResponseEntity<RedisEntity> put(@RequestBody RedisEntity param) {
		return ResponseEntity.ok(sampleService.put(param));
	}

	/*
	 * redis 조회
	 */
	@ApiOperation(value = "redis 조회", notes = "id 파라메터로 redis에 저장된 정보 조회")
	@GetMapping("{id}")
	public ResponseEntity<RedisEntity> get(@PathVariable String id) {
		return ResponseEntity.ok(sampleService.get(id));
	}

	/*
	 * redis Count 조회
	 */
	@ApiOperation(value = "redis Count 조회", notes = "redis에 저장된 총 count 조회")
	@GetMapping("count")
	public ResponseEntity<Long> getCount() {
		return ResponseEntity.ok(sampleService.getCount());
	}

	/*
	 * redis 전체 조회
	 */
	@ApiOperation(value = "redis 전체 조회", notes = "redis에 저장된 전체 데이터 조회")
	@GetMapping
	public ResponseEntity<List<RedisEntity>> getAll() {
		return ResponseEntity.ok(sampleService.getAll());
	}

	/*
	 * redis 삭제 - Entity
	 */
	@ApiOperation(value = "redis 삭제 - Entity", notes = "RedisEntity 파라메터로 redis에 저장된 정보 삭제")
	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody RedisEntity param) {
		sampleService.delete(param);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*
	 * redis 삭제 - id
	 */
	@ApiOperation(value = "redis 삭제 - id", notes = "id 파라메터로 redis에 저장된 정보 삭제")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		sampleService.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
