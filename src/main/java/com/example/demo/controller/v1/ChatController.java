
package com.example.demo.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.example.demo.service.gptchat.ChatService;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

  @Autowired
  private ChatService chatService;

  /** JSON POST: { "query": "..." } -> { "answer": "..." } */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
               produces = MediaType.APPLICATION_JSON_VALUE)
  public AnswerDto chat(@RequestBody QueryDto body) {
    // Basic server-side guardrails
    String q = body.query == null ? "" : body.query.trim();
    if (q.isEmpty()) return new AnswerDto("(empty query)");
    String answer = chatService.ask(q);
    return new AnswerDto(answer);
  }

  /** Optional: Server-Sent Events streaming endpoint (text/event-stream). */
  @GetMapping(path="/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter stream(@RequestParam("q") String q) throws IOException {
    // For demo, we simulate chunking a single answer.
    // In production, wire this to the SDK's streaming support when available.
    SseEmitter emitter = new SseEmitter(Duration.ofMinutes(2).toMillis());
    new Thread(() -> {
      try {
        String ans = chatService.ask(q);
        // naive chunking by sentences
        for (String part : ans.split("(?<=\\.)\\s+")) {
          emitter.send(part);
          Thread.sleep(80); // small delay for UX
        }
        emitter.complete();
      } catch (Exception e) {
        try { emitter.send("ERROR: " + e.getMessage()); } catch (IOException ignored) {}
        emitter.completeWithError(e);
      }
    }).start();
    return emitter;
  }

  /**
   * 오늘의 날씨와 날짜에 어울리는 노래 리스트를 제공
   * @return
   */
  @GetMapping("/today-music")
  public ResponseEntity<?> getTodaysMusic() {
    List<String> songList = new ArrayList<>();
    return ResponseEntity.ok(songList);
  }

  public static class QueryDto { public String query; }
  public static class AnswerDto {
    public String answer;
    public AnswerDto(String a) { this.answer = a; }
  }
}
