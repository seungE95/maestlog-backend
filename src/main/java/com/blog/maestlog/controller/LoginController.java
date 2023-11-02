package com.blog.maestlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.maestlog.dto.UserDto;
import com.blog.maestlog.service.MailService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/api/user")
public class LoginController {
	
	private final MailService mailService;
	
	@PostMapping("/login")
	public void sendMail(@RequestBody UserDto userDto) {
		mailService.mailSend(userDto);
	}
	
	
}
