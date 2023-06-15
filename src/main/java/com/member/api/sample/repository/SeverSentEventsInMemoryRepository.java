package com.member.api.sample.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.member.api.sample.entity.ServerSentEventsEmitterEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SeverSentEventsInMemoryRepository {
  private final Map<String, ServerSentEventsEmitterEntity> sseEmitterMap = new ConcurrentHashMap<>();

  public void put(String key, ServerSentEventsEmitterEntity entity) {
    sseEmitterMap.put(key, entity);
    log.debug(">>> put [{}] : {}", key, sseEmitterMap);
  }

  public Optional<ServerSentEventsEmitterEntity> get(String key) {
    return Optional.ofNullable(sseEmitterMap.get(key));
  }

  public Map<String, ServerSentEventsEmitterEntity> getAll() {
    return sseEmitterMap;
  }

  public Map<String, ServerSentEventsEmitterEntity> getRetryMap(String keyPrefix, String lastEventId) {
    return sseEmitterMap.entrySet().stream().filter(entry -> entry.getKey().startsWith(keyPrefix) && lastEventId.compareTo(entry.getKey()) < 0)
        .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  public ServerSentEventsEmitterEntity getEmitterEntityByStartWithIdLast(String keyPrefix) {
    return sseEmitterMap.entrySet().stream().filter(entry -> entry.getKey().startsWith(keyPrefix)).max(Map.Entry.comparingByKey())
        .map(Map.Entry::getValue).orElse(null);
  }

  public void remove(String key) {
    sseEmitterMap.remove(key);
  }
}
