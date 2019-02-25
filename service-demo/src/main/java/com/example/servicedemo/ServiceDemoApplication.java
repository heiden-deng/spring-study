package com.example.servicedemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.servicedemo.controller.Policy;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ServiceDemoApplication {

	public static void main(String[] args) {

		long time = new Date().getTime();

		String config = "{\"refreshInterval\":60,\"limit\":5,\"quota\":1000,\"type\":[\"USER\"]}";
		Gson gson = new Gson();
		Policy policy = gson.fromJson(config, Policy.class);

		JSONObject map=new JSONObject();
		String type_req = "[\"USER\"]";
		map.put("limit",15);
		map.put("quota",15);
		map.put("refreshInterval",60);
		String type = type_req.replaceAll("\\[","").replaceAll("]","").replaceAll("\"","");

		if (type.length() == 0){
			JSONArray types = new JSONArray();
			map.put("type",types);
		} else {
			String[] types = type.split(",");
			map.put("type",types);
		}

		System.out.println("json:" + map.toString());
		String config_new = map.toString();
		String type_value = map.getJSONArray("type").toString();
		Policy policy1 = gson.fromJson(config_new, Policy.class);


		SpringApplication.run(ServiceDemoApplication.class, args);
	}
}
