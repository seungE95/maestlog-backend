package com.blog.maestlog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private String email;
	private String nickname;
	private String unique_nickname;
	private String profile_photo;
}
