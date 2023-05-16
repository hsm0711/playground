package com.member.api.sample.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.member.api.sample.entity.ExcelDownEntity;
import com.member.api.sample.repository.ExcelDownRepository;
import com.member.utils.ExcelDownUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService {
	private final ExcelDownRepository excelDownRepository;

	public ResponseEntity<HttpServletResponse> download(HttpServletResponse response) {
		List<ExcelDownEntity> list = excelDownRepository.findAll();

		ExcelDownUtil<ExcelDownEntity> excel = new ExcelDownUtil<>(response, "파일명", "Sheet명", ExcelDownEntity.class, list);

		return excel.download();
	}
}
