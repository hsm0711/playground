package com.member.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;

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
	private CellStyle headerStyle;
	private CellStyle bodyStyle;

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
		workbook.setCompressTempFiles(true);

		sheet = workbook.createSheet(sheetName);

		getColumnInfo();

		makeExcel();
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

	private void getColumnInfo() {
		List<Field> fieldList = FieldUtils.getFieldsListWithAnnotation(clazz, ExcelDown.class);

		if (CollectionUtils.isEmpty(fieldList)) {
			// TODO Exception annotation 붙어있는 필드가 없는경우 Exception 처리
		}

		columnList = fieldList.stream().map(field -> {
			Annotation annotation = field.getAnnotation(ExcelDown.class);
			Map<String, Object> dtoInfoMap = AnnotationUtils.getAnnotationAttributes(annotation);
			dtoInfoMap.put("key", field.getName());

			return modelMapper.map(dtoInfoMap, ColumnInfo.class);
		})
		.sorted(Comparator.comparing(ColumnInfo::getOrder))
		.collect(Collectors.toList());

		log.debug(">>> columnList : {}", columnList);

		if (CollectionUtils.isEmpty(columnList)) {
			// TODO 컬럼 목록 없을때 Exception 처리
		}
	}

	private void makeExcel() {
		setHeader();

		if (CollectionUtils.isNotEmpty(dataList)) {
			setBody();
		}

		// TODO header 에서 width없으면 auto width 처리
	}

	private void setHeader() {
		Row row = sheet.createRow(0);

		for (int i = 0; i < columnList.size(); i++) {
			Cell cell = row.createCell(i);
			ColumnInfo columnInfo = columnList.get(i);

			cell.setCellValue(columnInfo.getHeaderName());

			if (headerStyle != null) {
				cell.setCellStyle(headerStyle);
			}

			if (columnInfo.getWith() > 0) {
				sheet.setColumnWidth(i, columnInfo.getWith());
			}
		}
	}

	private void setBody() {

		for (int i = 0; i < dataList.size(); i++) {
			Row row = sheet.createRow(i+1);
			T dataObj = dataList.get(i);

			log.debug(">>> columnList : {}", columnList);

			for (int j = 0; j < columnList.size(); j++) {
				Cell cell = row.createCell(j);
				ColumnInfo columnInfo = columnList.get(j);

				String value = "";

				try {
					value = StringUtils.defaultString(Objects.toString(FieldUtils.readField(dataObj, columnInfo.getKey(), true)));
				} catch (IllegalAccessException e) {
					Method getMethod = ReflectionUtils.findMethod(clazz, "get" + StringUtils.capitalize(columnInfo.getKey()));

					if (getMethod != null) {
						value = StringUtils.defaultString(Objects.toString(ReflectionUtils.invokeMethod(getMethod, dataObj)));
					} else {
						// TODO 이상황까지 오려나.. 일단 나중에 처리 생각 해보기
					}
				}

				// TODO cell 최대 글자 처리

				cell.setCellValue(value);

				if (columnInfo.getCellStyle() != null) {
					cell.setCellStyle(columnInfo.getCellStyle());
				} else if (StringUtils.isNotBlank(columnInfo.getDataFormat()) || bodyStyle != null) {
					CellStyle cellStyle = null;

					if (bodyStyle != null) {
						cellStyle = bodyStyle;
					}

					if (StringUtils.isNotBlank(columnInfo.getDataFormat())) {
						if (cellStyle == null) {
							cellStyle = workbook.createCellStyle();
						}

						DataFormat dataFormat = workbook.createDataFormat();

						cellStyle.setDataFormat(dataFormat.getFormat(columnInfo.getDataFormat()));
					}

					if (cellStyle != null) {
						cell.setCellStyle(cellStyle);

						columnInfo.setCellStyle(cellStyle);
						columnList.set(i, columnInfo);
					}
				}
			}
		}
	}

	public ResponseEntity<byte[]> download() {
		byte[] bytes = null;

		try (
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
			) {
			workbook.write(bos);
			bytes = bos.toByteArray();
			workbook.dispose();
		} catch (IOException e) {
			// TODO Exception 처리
		}

		return ResponseEntity.ok()
				.cacheControl(CacheControl.noCache())
				.header(HttpHeaders.CONTENT_TYPE, "application/ms-excel")
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx;")
				.body(bytes);
		// 파일명 설정
		// 파일다운
	}

	@ToString
	@Getter
	@Setter
	public static class ColumnInfo {
		private String key;
        private String headerName;
        private int with;
        private int order;
        private String dataFormat;
        private CellStyle cellStyle;
    }
}
