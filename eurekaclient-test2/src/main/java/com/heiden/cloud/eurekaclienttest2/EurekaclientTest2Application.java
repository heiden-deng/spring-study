package com.heiden.cloud.eurekaclienttest2;

import com.heiden.cloud.eurekaclienttest2.service.CategraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaclientTest2Application {




	public static void main(String[] args) {
		SpringApplication.run(EurekaclientTest2Application.class, args);
	}

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
	  return   new RestTemplate();
    }
    //= new RestTemplate();




}
