package com.member.api.sample;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.api.sample.service.ExcelService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Excel 샘플", description = "Excel 샘플 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/public/sample/excel")
public class ExcelDownController {
	private final ExcelService excelService;

	/**
	 * Excel download
	 */
	@GetMapping("/download")
	public ResponseEntity<HttpServletResponse> download(HttpServletResponse response) {
		return excelService.download(response);
	}
}
