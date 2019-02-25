package com.heiden.cloud.grayreleasetest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class GrayReleaseTestApplication {

	@Value("${server.port}")
	private String port;

	@RequestMapping("/hello")
	public String sayHello(){
		return "hello, I from " + port;
	}

	public static void main(String[] args) {
		SpringApplication.run(GrayReleaseTestApplication.class, args);
	}
}
