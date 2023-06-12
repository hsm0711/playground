package com.member.api.sample.entity;

import javax.validation.constraints.NotBlank;
import com.member.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisPublishEntity extends BaseEntity {
  @NotBlank(message = "topic은 필수 값입니다.")
  private String topic;

  @NotBlank(message = "message는 필수 값입니다.")
  private Object message;
}
