package com.member.api.sample.model;

import com.member.model.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SseDto extends BaseDto {
  private String id;

  private Long sendDate;

  private Object data;

}
