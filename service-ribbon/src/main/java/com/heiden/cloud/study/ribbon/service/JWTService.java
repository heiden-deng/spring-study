package com.heiden.cloud.study.ribbon.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JWTService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String loginService(String name, String password) {
        return restTemplate.getForObject("http://service1-hi/login?name=" + name + "&password=" + password, String.class);
    }

    public String hiError(String name, String password) {
        return "hi," + name + ",sorry, error!";
    }
}
