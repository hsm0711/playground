package com.playground.api.sample;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.playground.api.sample.entity.RedisPublishEntity;
import com.playground.api.sample.entity.RedisRepositoryEntity;
import com.playground.api.sample.entity.RedisTemplateEntity;
import com.playground.api.sample.service.RedisService;
import com.playground.model.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "redis", description = "Redis 샘플 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/playground/public/sample/redis")
public class RedisController {
  private final RedisService sampleService;

  /**
   * redis 저장 - redisRepository
   */
  @Operation(summary = "redis 저장 - redisRepository", description = "redis 저장")
  @PutMapping("/repository")
  public ResponseEntity<BaseResponse<RedisRepositoryEntity>> putRepository(@RequestBody RedisRepositoryEntity param) {
    return ResponseEntity.ok(new BaseResponse<>(sampleService.putRepository(param)));
  }

  /**
   * redis 조회 - redisRepository
   */
  @Operation(summary = "redis 조회 - redisRepository", description = "id 파라메터로 redis에 저장된 정보 조회")
  @GetMapping("/repository/{id}")
  public ResponseEntity<BaseResponse<RedisRepositoryEntity>> getRepository(@PathVariable String id) {
    return ResponseEntity.ok(new BaseResponse<>(sampleService.getRepository(id)));
  }

  /**
   * redis Count 조회 - redisRepository
   */
  @Operation(summary = "redis Count 조회 - redisRepository", description = "redis에 저장된 총 count 조회")
  @GetMapping("/repository/count")
  public ResponseEntity<BaseResponse<Long>> getCountRepository() {
    return ResponseEntity.ok(new BaseResponse<>(sampleService.getCountRepository()));
  }

  /**
   * redis 전체 조회 - redisRepository
   */
  @Operation(summary = "redis 전체 조회 - redisRepository", description = "redis에 저장된 전체 데이터 조회")
  @GetMapping("/repository")
  public ResponseEntity<BaseResponse<List<RedisRepositoryEntity>>> getAllRepository() {
    return ResponseEntity.ok(new BaseResponse<>(sampleService.getAllRepository()));
  }

  /**
   * redis 삭제 - Entity - redisRepository
   */
  @Operation(summary = "redis 삭제 - Entity - redisRepository", description = "RedisEntity 파라메터로 redis에 저장된 정보 삭제")
  @DeleteMapping("/repository")
  public ResponseEntity<Void> deleteRepository(@RequestBody RedisRepositoryEntity param) {
    sampleService.deleteRepository(param);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * redis 삭제 - id - redisRepository
   */
  @Operation(summary = "redis 삭제 - id - redisRepository", description = "id 파라메터로 redis에 저장된 정보 삭제")
  @DeleteMapping("/repository/{id}")
  public ResponseEntity<Void> deleteByIdRepository(@PathVariable String id) {
    sampleService.deleteByIdRepository(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * redis 저장 - redisTemplate
   */
  @Operation(summary = "redis 저장 - redisTemplate", description = "redis 저장")
  @PutMapping("/template")
  public ResponseEntity<Void> putTemplate(@RequestBody RedisTemplateEntity param) {
    sampleService.putTemplate(param);

    return ResponseEntity.ok().build();
  }

  /**
   * redis 조회 - redisTemplate
   */
  @Operation(summary = "redis 조회 - redisTemplate", description = "id 파라메터로 redis에 저장된 정보 조회")
  @GetMapping("/template/{id}")
  public ResponseEntity<BaseResponse<RedisTemplateEntity>> getTemplate(@PathVariable String id) {
    return ResponseEntity.ok(new BaseResponse<>(sampleService.getTemplate(id)));
  }

  /**
   * redis Count 조회 - redisTemplate
   */
  @Operation(summary = "redis Count 조회 - redisTemplate", description = "redis에 저장된 총 count 조회")
  @GetMapping("/template/count")
  public ResponseEntity<BaseResponse<Long>> getCountTemplate() {
    return ResponseEntity.ok(new BaseResponse<>(sampleService.getCountTemplate()));
  }

  /**
   * redis 전체 조회 - redisTemplate
   */
  @Operation(summary = "redis 전체 조회 - redisTemplate", description = "redis에 저장된 전체 데이터 조회")
  @GetMapping("/template")
  public ResponseEntity<BaseResponse<List<RedisTemplateEntity>>> getAllTemplate() {
    return ResponseEntity.ok(new BaseResponse<>(sampleService.getAllTemplate()));
  }

  /**
   * redis 삭제 - Entity - redisTemplate
   */
  @Operation(summary = "redis 삭제 - Entity - redisTemplate", description = "RedisEntity 파라메터로 redis에 저장된 정보 삭제")
  @DeleteMapping("/template")
  public ResponseEntity<Void> deleteTemplate(@RequestBody RedisTemplateEntity param) {
    sampleService.deleteTemplate(param);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * redis 삭제 - id - redisTemplate
   */
  @Operation(summary = "redis 삭제 - id - redisTemplate", description = "id 파라메터로 redis에 저장된 정보 삭제")
  @DeleteMapping("/template/{id}")
  public ResponseEntity<Void> deleteByIdTemplate(@PathVariable String id) {
    sampleService.deleteByIdTemplate(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * redis pub/sub - publish
   */
  @Operation(summary = "redis pub/sub - publish", description = "redis의 topic 구독자에 메시지 전송")
  @PostMapping("/publish")
  public ResponseEntity<Void> publishTopic(@RequestBody RedisPublishEntity redisPublishEntity) {
    sampleService.publishTopic(redisPublishEntity);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
