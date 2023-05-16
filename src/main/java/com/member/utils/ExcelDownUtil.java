package com.member.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;

import com.member.annotation.ExcelDown;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelDownUtil<T> {
	private static final int MAX_ROWS = SpreadsheetVersion.EXCEL2007.getMaxRows();
	private static final int FLUSH_ROWS = 10000;

	private static final ModelMapper modelMapper = new ModelMapper();

	private HttpServletResponse response;
	private String fileName;
	private Class<T> clazz;
	private List<ColumnInfo> columnList;
	private List<T> dataList;
	private SXSSFWorkbook workbook;
	private SXSSFSheet sheet;

	/**
	 *
	 * @param response - Download 처리를 위한 HttpServlerResponse
	 * @param fileName - 다운로드 될 파일 명
	 * @param sheetName - 시트명
	 * @param clazz - 다운로드하는 DTO/VO Class
	 * @param dataList - 다운로드할 data 목록
	 */
	public ExcelDownUtil(HttpServletResponse response, String fileName, String sheetName, Class<T> clazz, List<T> dataList) {
		this.response = response;
		this.fileName = fileName;
		this.clazz = clazz;
		this.dataList = dataList;

		if (CollectionUtils.isNotEmpty(dataList) && dataList.size() > MAX_ROWS) {
			// TODO max row 초과 Exception
		}

		workbook = new SXSSFWorkbook(FLUSH_ROWS);
		sheet = workbook.createSheet(sheetName);

		makeColumnInfo();

		// header annotation 정보 정리
		// body annotation 정보 정리
		// 필수값 validation
		// makeExcel
		// download
	}

	/**
	 *
	 * @param response - Download 처리를 위한 HttpServlerResponse
	 * @param fileName - 다운로드 될 파일/sheet 명
	 * @param clazz - 다운로드하는 DTO/VO Class
	 * @param dataList - 다운로드할 data 목록
	 */
	public ExcelDownUtil(HttpServletResponse response, String fileName, Class<T> clazz, List<T> dataList) {
		this(response, fileName, fileName, clazz, dataList);
	}

	private void makeColumnInfo() {
		List<Field> fieldList = FieldUtils.getFieldsListWithAnnotation(clazz, ExcelDown.class);

		if (CollectionUtils.isEmpty(fieldList)) {
			// TODO Exception annotation 붙어있는 필드가 없는경우 Exception 처리
		}

		columnList = fieldList.stream().map(field -> {
			Annotation annotation = field.getAnnotation(ExcelDown.class);

			return modelMapper.map(AnnotationUtils.getAnnotationAttributes(annotation), ColumnInfo.class);
		}).collect(Collectors.toList());
	}

	private void makeExcel() {
		// setHeader
		// setBody
		// with 있으면 처리
	}

	private void setHeader() {
		// header style 체크
		// header data 처리
		// dataFormat있으면 처리
	}

	private void setBody() {
		// body style 체크
		// body data 처리, cell 최대 글자 처리
		// header 에서 width없으면 auto width 처리
	}

	public ResponseEntity<HttpServletResponse> download() {
		return ResponseEntity.ok().body(response);
		// 파일명 설정
		// 파일다운
	}

	@ToString
	@Getter
	@Setter
	public static class ColumnInfo {
        private String headerName;
        private int with;
        private CellType cellType;
        private String dataFormat;
    }
}
