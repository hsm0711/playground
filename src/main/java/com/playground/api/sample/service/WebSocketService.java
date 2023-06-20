package com.playground.api.sample.service;


import java.time.LocalDateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.playground.api.sample.model.WebSocketDto;
import com.playground.constants.RedisSubscibeChannel;
import com.playground.constants.WebSocketTargetType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebSocketService {
  private final RedisTemplate<String, Object> redisTemplate;

  public void sendMessage(WebSocketDto webSocketDto) {
    webSocketDto.setTargetType(WebSocketTargetType.USER);
    webSocketDto.setSendDate(LocalDateTime.now());

    redisTemplate.convertAndSend(RedisSubscibeChannel.WEBSOCKET_TOPIC.name(), webSocketDto);
  }

  public void sendAllMessage(WebSocketDto webSocketDto) {
    webSocketDto.setTargetType(WebSocketTargetType.ALL);
    webSocketDto.setSendDate(LocalDateTime.now());

    redisTemplate.convertAndSend(RedisSubscibeChannel.WEBSOCKET_TOPIC.name(), webSocketDto);
  }
}
