package com.blog.maestlog.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.maestlog.service.firstService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class firstController {
	
	private final firstService firstService;
	
	@GetMapping("/first")
	public Map<String, Object> firstController(){
		return firstService.getFirstService();
	}
}
