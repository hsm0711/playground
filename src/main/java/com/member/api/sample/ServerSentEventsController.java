package com.member.api.sample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.member.api.sample.model.SseDto;
import com.member.api.sample.service.ServerSentEventsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "SSE(Server Sent Events)(EventStream) 샘플 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member/public/sample/sse")
public class ServerSentEventsController {
  private final ServerSentEventsService serverSentEventsService;

  /**
   * sse publish
   */
  @ApiOperation(value = "sse 메시지 발송", notes = "sse의 연결된 사용자(id)에게 메시지 전송")
  @PostMapping("/send-message")
  public ResponseEntity<Void> sendMessage(@RequestBody SseDto sseDto) {
    serverSentEventsService.sendMessage(sseDto);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * sse 연결
   */
  @ApiOperation(value = "sse 구독", notes = "sse구독")
  @GetMapping(value = "/subscribe/{id}", produces = "text/event-stream")
  public SseEmitter subscribe(@PathVariable String id,
      @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
    return serverSentEventsService.subscribe(id, lastEventId);
  }
}
