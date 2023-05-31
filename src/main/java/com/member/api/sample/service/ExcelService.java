package com.member.api.sample.service;

import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
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

  public ResponseEntity<byte[]> download() {
    List<ExcelDownEntity> list = excelDownRepository.findAll();

    ExcelDownUtil<ExcelDownEntity> excel = new ExcelDownUtil<>("파일명", "Sheet명", ExcelDownEntity.class, list);

    return excel.download();
  }

  public ResponseEntity<byte[]> downloadWithStyle() {
    List<ExcelDownEntity> list = excelDownRepository.findAll();

    ExcelDownUtil<ExcelDownEntity> excel = new ExcelDownUtil<>("스타일 적용", "스타일 적용한 Sheet", ExcelDownEntity.class, list);

    CellStyle headerStyle = excel.getEmptyStyle();
    headerStyle.setAlignment(HorizontalAlignment.CENTER);
    headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    headerStyle.setBorderTop(BorderStyle.THIN);
    headerStyle.setBorderRight(BorderStyle.THIN);
    headerStyle.setBorderBottom(BorderStyle.THIN);
    headerStyle.setBorderLeft(BorderStyle.THIN);
    headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    Font headerFont = excel.getFont();
    headerFont.setBold(true);

    excel.setHeaderStyle(headerStyle);

    CellStyle bodyStyle = excel.getEmptyStyle();
    bodyStyle.setBorderTop(BorderStyle.THIN);
    bodyStyle.setBorderRight(BorderStyle.THIN);
    bodyStyle.setBorderBottom(BorderStyle.THIN);
    bodyStyle.setBorderLeft(BorderStyle.THIN);

    excel.setBodyStyle(bodyStyle);

    return excel.download();
  }
}
