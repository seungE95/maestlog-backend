package com.blog.maestlog.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.blog.maestlog.dto.UserDto;
import com.blog.maestlog.service.MailService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{

	private JavaMailSender mailSender;
	private static final String FROM_MAIL = "ewotmd95@naver.com";
	
	@Override
	public void mailSend(UserDto userDto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userDto.getEmail());
		message.setFrom(MailServiceImpl.FROM_MAIL);
		message.setSubject("안녕하세요! Maestlog입니다!");
		message.setText("로그인 하시려면 ➡️ http://localhost:8080/api/user/token 을 눌러주세요!");
		System.out.println("\nmessage ::: "+ message.toString());
		mailSender.send(message);
	}

}
