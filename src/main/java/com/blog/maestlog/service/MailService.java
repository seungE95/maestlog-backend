package com.blog.maestlog.service;

import com.blog.maestlog.dto.UserDto;

public interface MailService {

	void mailSend(UserDto userDto);

}
