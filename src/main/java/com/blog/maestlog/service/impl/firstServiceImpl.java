package com.blog.maestlog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.blog.maestlog.service.firstService;

@Service
public class firstServiceImpl implements firstService{

	@Override
	public Map<String, Object> getFirstService() {

		Map<String, Object> firstData = new HashMap<>();
		
		firstData.put("label1", "check1");
		firstData.put("label2", "check2");
		firstData.put("label3", "check3");
		
		return firstData;
	}
}
