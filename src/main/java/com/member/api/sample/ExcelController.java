package com.member.api.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.api.sample.service.ExcelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Excel 샘플 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/public/sample/excel")
public class ExcelController {
  private final ExcelService excelService;

  /**
   * Excel download
   */
  @ApiOperation(value = "Excel download", notes = "Excel 다운로드 샘플")
  @GetMapping("/download")
  public ResponseEntity<byte[]> download() {
    return excelService.download();
  }

  /**
   * Excel download With Style
   */
  @ApiOperation(value = "Excel download With Style", notes = "Cell에 스타일이 적용된 Excel 다운로드 샘플")
  @GetMapping("/download-style")
  public ResponseEntity<byte[]> downloadWithStyle() {
    return excelService.downloadWithStyle();
  }
}
