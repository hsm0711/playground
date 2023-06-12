package com.member.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {

  @Override
  public void onMessage(Message message, byte[] pattern) {
    log.debug(">>> RedisMessageSubscriber.onMessage : {}", message);
  }
}
