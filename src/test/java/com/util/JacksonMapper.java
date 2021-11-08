package com.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapper {

	private ObjectMapper objectMapper = new ObjectMapper();

	public <T> String serializeClassToJsonString(T tClass) throws Exception {
		return objectMapper.writeValueAsString(tClass);
	}

	public <T> T deserializeJsonToClass(String response, Class<T> tClass) throws IOException {
		return objectMapper.readValue(response, tClass);
	}

}
