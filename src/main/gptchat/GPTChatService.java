package com.example.ai;

import com.openai.client.OpenAIClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
  private final OpenAIClient client;

  public ChatService(OpenAIClient client) {
    this.client = client;
  }

  /** Simple single-turn request using the Responses API. */
  public String ask(String userQuery) {
    // Build a minimal prompt. You can enrich with system instructions if needed.
    ResponseCreateParams params = ResponseCreateParams.builder()
        .model(ChatModel.GPT_5) // or ChatModel.GPT_4_1, etc.
        .input(userQuery)
        .build();

    Response res = client.responses().create(params);
    // The SDK returns a structured Response; extract the text content.
    // Helper: most common case is a single output text.
    return res.outputText().orElse("(no content)");
  }
}
