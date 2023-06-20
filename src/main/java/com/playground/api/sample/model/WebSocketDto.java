package com.playground.api.sample.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.playground.constants.WebSocketMessageType;
import com.playground.constants.WebSocketTargetType;
import com.playground.model.BaseDto;
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
public class WebSocketDto extends BaseDto {

  @JsonInclude(Include.NON_NULL)
  private WebSocketTargetType targetType;

  private WebSocketMessageType messageType;

  private LocalDateTime sendDate;

  private String senderId;

  @JsonInclude(Include.NON_NULL)
  private String receiverId;

  private String message;

}
