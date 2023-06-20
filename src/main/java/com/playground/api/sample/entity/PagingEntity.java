package com.playground.api.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.playground.annotation.Secret;
import com.playground.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class PagingEntity extends BaseEntity {

  /**
   * 아이디
   */
  @Id
  @Column(name = "user_id")
  private String userId;

  /**
   * 비밀번호
   */
  @Secret
  @Column
  private String password;

  /**
   * 이름
   */
  @Column
  private String name;

  /**
   * 이메일
   */
  @Column
  private String email;

  @Builder
  public PagingEntity(String userId, String password, String name, String email) {
    super();
    this.userId = userId;
    this.password = password;
    this.name = name;
    this.email = email;
  }

}
