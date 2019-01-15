package com.leting.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperSupport {

	public static <T> List<T> transJsonStrToList(String jsonStr,Class<T> className){
		
		List<T> list = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, className);
		try {
			list = mapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
} 
