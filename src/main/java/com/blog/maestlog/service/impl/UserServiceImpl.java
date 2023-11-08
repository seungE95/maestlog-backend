package com.blog.maestlog.service.impl;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.blog.maestlog.dao.UserDao;
import com.blog.maestlog.service.UserService;
import com.blog.maestlog.vo.UserVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private JavaMailSender mailSender;
	//private final 
	private static final String FROM_MAIL = "ewotmd95@naver.com";
	private final UserDao userDao;
	
	@Override
	public void sendMail(UserVo userVo, String token) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userVo.getEmail());
		message.setFrom(UserServiceImpl.FROM_MAIL);
		message.setSubject("안녕하세요! Maestlog입니다!");
		message.setText("로그인 하시려면 ➡ '"+token+"' 을 눌러주세요!");

		mailSender.send(message);
	}

	@Override
	public Map<String, String> userJoin(String email) {
		Map<String, String> result = null;
		result = userDao.emailCheck(email);
		System.out.println("\nlist ::: "+result);
		
		return result;
	}

	@Override
	public void userCheck(String token) {
		// TODO Auto-generated method stub
		
	}

}
