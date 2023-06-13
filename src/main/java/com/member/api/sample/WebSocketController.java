package com.member.api.sample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.member.api.sample.model.WebSocketDto;
import com.member.api.sample.service.WebSocketService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
  private final WebSocketService websocketService;

  /**
   * websocket publish
   */
  @MessageMapping("/send-message")
  @PostMapping("/member/public/sample/websocket/send-message")
  public ResponseEntity<Void> sendMessage(@RequestBody WebSocketDto webSocketDto) {
    websocketService.sendMessage(webSocketDto);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * websocket publish
   */
  @MessageMapping("/send-all-message")
  @PostMapping("/member/public/sample/websocket/send-all-message")
  public ResponseEntity<Void> sendAllMessage(@RequestBody WebSocketDto webSocketDto) {
    websocketService.sendAllMessage(webSocketDto);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
