spring:
  messages:
    basename: messages
    encoding: UTF-8
    fallback-to-system-locale: false


package com.example.mywebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 브라우저 Accept-Language를 따르고, 기본을 한국어로.
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.KOREAN);
        return resolver;
    }


@Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        registry.addInterceptor(lci);
    }
}


package com.example.mywebsite.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    // 홈 전용 쇼트컷 (필요 시)
    public String getHomeMessage() {
        return get("home.message");
    }
}


package com.example.mywebsite.controller;

import com.example.mywebsite.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final MessageService messages;

    public HomeController(MessageService messages) {
        this.messages = messages;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", messages.getHomeMessage());
        return "home/index"; // templates/home/index.html
    }
}


<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="ko">
<head>
  <meta charset="UTF-8" />
  <title th:text="#{site.title}">My Website</title>
  <link rel="stylesheet" th:href="@{/css/main.css}" />
</head>
<body>
  <main>
    <h1 th:text="${message}">홈 메시지</h1>

    <p>
      <a th:href="@{/?lang=ko}">한국어</a> |
      <a th:href="@{/?lang=en}">English</a>
    </p>
  </main>
  <script th:src="@{/js/main.js}"></script>
</body>
</html>


site.title=내 웹사이트
home.message=안녕하세요!

site.title=My Website
home.message=Hello! This is the home page with i18n enabled.


package com.example.mywebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyWebsiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyWebsiteApplication.class, args);
    }
}
