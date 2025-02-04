package com.ecommerce.product.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class CafeUtils {

	public static ResponseEntity<String> getResponseEntity(String message, HttpStatus status) {
		return new ResponseEntity<String>(message, status);
	}

	public static String genrateUuid() {
		
		UUID randomUUID = UUID.randomUUID();
		 
		return "Bill"+randomUUID;
	}

	public static JsonArray getJsonArrayFromString(String data) throws JsonIOException {
//		JsonArray array = new JsonArray(data);
		 
		return JsonParser.parseString(data).getAsJsonArray();

	}

	public static Map<String, Object> getMapFromJson(String data) {

		if (!Strings.isEmpty(data) && data != null)
			return new Gson().fromJson(data, new TypeToken<Map<String, Object>>() {
         private static final long serialVersionUID = 1L;
			}.getType());

		return new HashMap<>();

	}

}
