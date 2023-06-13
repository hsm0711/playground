package com.member.api.sample.service;


import java.time.LocalDateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.member.api.sample.model.WebSocketDto;
import com.member.constants.RedisSubscibeChannel;
import com.member.constants.WebSocketTargetType;
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
