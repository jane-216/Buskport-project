package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.db.model.User;
import com.example.demo.model.UserCreateRequestDto;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUsers(@PathVariable long userId) {
		User result = userService.getUser(userId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping()
	public ResponseEntity<?> postUsers(@RequestBody UserCreateRequestDto user) {
		System.out.println("jmjr");
		System.out.println(user);
		userService.addUser(user);
		return ResponseEntity.accepted().build();
	}
}
