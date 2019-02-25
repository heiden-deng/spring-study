package com.example.serviceread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Configuration
public class ServiceReadApplication {

	@Autowired
	ServiceConfig serviceConfig;
	public static void main(String[] args) {

		SpringApplication.run(ServiceReadApplication.class, args);
	}

	@GetMapping("/print")
	public String test(){
		return serviceConfig.getUrl();
	}
}
