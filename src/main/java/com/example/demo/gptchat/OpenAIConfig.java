package com.example.demo.gptchat;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {

  /** Build OpenAI client from environment variables (OPENAI_API_KEY, etc.). */
  @Bean
  public OpenAIClient openAIClient() {
    return OpenAIOkHttpClient.fromEnv();
  }
}
