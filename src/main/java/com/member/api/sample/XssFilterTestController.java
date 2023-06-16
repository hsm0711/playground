package com.member.api.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.member.api.sample.model.WebSocketDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/public/sample/xss")
public class XssFilterTestController {
  @PostMapping("/request-body")
  public ResponseEntity<WebSocketDto> requestBodyTest(@RequestBody WebSocketDto webSocketDto) {
    return ResponseEntity.ok(webSocketDto);
  }

  @GetMapping("/get")
  public ResponseEntity<String> getTest(@RequestParam String param) {
    return ResponseEntity.ok(param);
  }
}
