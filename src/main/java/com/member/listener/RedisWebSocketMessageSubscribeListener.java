package com.member.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import com.member.api.sample.model.WebSocketDto;
import com.member.constants.WebSocketTargetType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisWebSocketMessageSubscribeListener implements MessageListener {
  private final RedisTemplate<String, Object> redisTemplate;
  private final SimpMessageSendingOperations messagingTemplate;

  /**
   * Redis에서 메시지가 발행(publish)되면 대기하고 있던 onMessage가 해당 메시지를 받아 처리한다.
   */
  @Override
  public void onMessage(Message message, byte[] pattern) {
    WebSocketDto messageDto = (WebSocketDto) redisTemplate.getHashValueSerializer().deserialize(message.getBody());

    if (messageDto != null) {
      if (WebSocketTargetType.ALL.equals(messageDto.getTargetType())) {
        messageDto.setTargetType(null);
        messagingTemplate.convertAndSend("/sub", messageDto);
      } else if (WebSocketTargetType.USER.equals(messageDto.getTargetType())) {
        messageDto.setTargetType(null);
        messagingTemplate.convertAndSendToUser(messageDto.getReceiverId(), "/direct", messageDto);
      }
    }
  }
}
