package com.member.api.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.poi.ss.usermodel.CellType;

import com.member.annotation.ExcelDown;
import com.member.annotation.Secret;
import com.member.entity.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="member")
public class ExcelDownEntity extends BaseEntity {

	@Id
	@Column(name = "user_id")
	@ExcelDown(headerName = "아이디", cellType = CellType.STRING, dataFormat = "내 아이디는 : @", order = 1, width = 400)
	private String userId;

	@Secret
	@Column
	@ExcelDown(headerName = "비밀번호", cellType = CellType.STRING)
	private String password;

	@Column
	@ExcelDown(headerName = "이름", cellType = CellType.STRING, order = 2)
	private String name;

	@Column(name = "reg_no")
	@ExcelDown(headerName = "주민등록번호", cellType = CellType.STRING, width = 50)
	private String regNo;

	@Builder
	public ExcelDownEntity(String userId, String password, String name, String regNo) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.regNo = regNo;
	}

}
