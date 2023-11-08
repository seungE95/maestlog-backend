package com.blog.maestlog.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
	Map<String, String> emailCheck(String email);
}
