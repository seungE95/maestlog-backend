package com.blog.maestlog.service;

import java.util.Map;

import com.blog.maestlog.vo.UserVo;

public interface UserService {
	void sendMail(UserVo userVo, String token);
	Map<String, String> userJoin(String email);
	void userCheck(String token);
	
}
