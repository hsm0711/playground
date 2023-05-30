package com.member.api.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.member.annotation.ExcelDown;
import com.member.annotation.Secret;
import com.member.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class ExcelDownEntity extends BaseEntity {

  @Id
  @Column(name = "user_id")
  @ExcelDown(headerName = "아이디", dataFormat = "내 아이디는 : @", order = 1, width = 150)
  private String userId;

  @Secret
  @Column
  @ExcelDown(headerName = "비밀번호")
  private String password;

  @Column
  @ExcelDown(headerName = "이름", order = 2)
  private String name;

  @ExcelDown(headerName = "이메일", width = 150)
  private String email;

  @Builder
  public ExcelDownEntity(String userId, String password, String name, String email) {
    super();
    this.userId = userId;
    this.password = password;
    this.name = name;
    this.email = email;
  }

}
