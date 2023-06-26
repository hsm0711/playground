package com.playground.api.sample;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.playground.api.sample.entity.PagingEntity;
import com.playground.api.sample.service.PagingService;
import com.playground.model.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "paging", description = "Paging 샘플 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/playground/public/sample/paging")
public class PagingController {
  private final PagingService pagingService;

  /**
   * paging list 조회
   */
  @Operation(summary = "paging list 조회", description = "paging list 조회")
  @GetMapping("/list")
  public ResponseEntity<BaseResponse<List<PagingEntity>>> getPagingList(Pageable pageable) {
    return ResponseEntity.ok(new BaseResponse<>(pagingService.getPagingList(pageable)));
  }

  /**
   * paging page 조회
   */
  @Operation(summary = "paging page 조회", description = "paging page 조회")
  @GetMapping("/page")
  public ResponseEntity<BaseResponse<Page<PagingEntity>>> getPagingPage(Pageable pageable) {
    return ResponseEntity.ok(new BaseResponse<>(pagingService.getPagingPage(pageable)));
  }

  /**
   * paging slice 조회
   */
  @Operation(summary = "paging slice 조회", description = "paging slice 조회")
  @GetMapping("/slice")
  public ResponseEntity<BaseResponse<Slice<PagingEntity>>> getPagingSlice(Pageable pageable) {
    return ResponseEntity.ok(new BaseResponse<>(pagingService.getPagingSlice(pageable)));
  }

  /**
   * paging list 조회
   */
  @Operation(summary = "paging list 조회", description = "paging list 조회")
  @PostMapping("/list")
  public ResponseEntity<BaseResponse<List<PagingEntity>>> postPagingList(Pageable pageable) {
    return ResponseEntity.ok(new BaseResponse<>(pagingService.getPagingList(pageable)));
  }

  /**
   * paging page 조회
   */
  @Operation(summary = "paging page 조회", description = "paging page 조회")
  @PostMapping("/page")
  public ResponseEntity<BaseResponse<Page<PagingEntity>>> postPagingPage(Pageable pageable) {
    return ResponseEntity.ok(new BaseResponse<>(pagingService.getPagingPage(pageable)));
  }

  /**
   * paging slice 조회
   */
  @Operation(summary = "paging slice 조회", description = "paging slice 조회")
  @PostMapping("/slice")
  public ResponseEntity<BaseResponse<Slice<PagingEntity>>> postPagingSlice(Pageable pageable) {
    return ResponseEntity.ok(new BaseResponse<>(pagingService.getPagingSlice(pageable)));
  }
}
