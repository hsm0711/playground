package com.member.api.sample.service;


import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.member.api.sample.entity.ServerSentEventsEmitterEntity;
import com.member.api.sample.model.SseDto;
import com.member.api.sample.repository.SeverSentEventsInMemoryRepository;
import com.member.constants.RedisSubscibeChannel;
import com.member.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServerSentEventsService {
  private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

  private final SeverSentEventsInMemoryRepository sseRepository;

  private final RedisTemplate<String, Object> redisTemplate;

  public void sendMessage(SseDto sseDto) {
    sseDto.setSendDate(System.currentTimeMillis());

    redisTemplate.convertAndSend(RedisSubscibeChannel.SSE_TOPIC.name(), sseDto);
  }

  public SseEmitter subscribe(String userId, String lastEventId) {
    String id = userId + "_" + System.currentTimeMillis();
    SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);

    ServerSentEventsEmitterEntity entity = new ServerSentEventsEmitterEntity();
    entity.setSseEmitter(sseEmitter);
    sseRepository.put(id, entity);

    sseEmitter.onCompletion(() -> sseRepository.remove(id));
    sseEmitter.onTimeout(sseEmitter::complete);

    // 3
    // 503 에러를 방지하기 위한 더미 이벤트 전송
    sendToClient(sseEmitter, id, "EventStream Created. [userId=" + userId + ", id=" + id + "]");

    // 4
    // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
    if (StringUtils.isNotBlank(lastEventId)) {
      Map<String, ServerSentEventsEmitterEntity> events = sseRepository.getRetryMap(userId, lastEventId);

      events.entrySet().stream().forEach(entry -> sendToClient(entry.getValue().getSseEmitter(), entry.getKey(), entry.getValue().getData()));
    }

    return sseEmitter;
  }

  // 3
  private void sendToClient(SseEmitter emitter, String id, Object data) {
    try {
      emitter.send(SseEmitter.event().id(id).name("sse").data(data));
    } catch (IOException exception) {
      sseRepository.remove(id);

      throw new CustomException("연결 오류!");
    }
  }
}
