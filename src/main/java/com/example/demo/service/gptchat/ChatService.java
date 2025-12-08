package com.example.demo.service.gptchat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
  private final ChatClient chatClient;

  public ChatService(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.build();
  }

  /** Simple single-turn request using the Responses API. */
  public String getChatResponse(String prompt) {
    Prompt chatPrompt = new Prompt(new UserMessage(prompt));

    ChatResponse response = chatClient.call(chatPrompt).block();

    if (response != null && response.getResult() != null && response.getResult().getOutput() != null) {
      return response.getResult().getOutput().getContent();
    }
        
    return "응답을 받아오지 못했습니다.";
  }
}
