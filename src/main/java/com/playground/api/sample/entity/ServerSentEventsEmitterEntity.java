package com.playground.api.sample.entity;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.playground.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerSentEventsEmitterEntity extends BaseEntity {
  private SseEmitter sseEmitter;

  private Object data;
}
