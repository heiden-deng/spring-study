package com.heiden.cloud.study.feign.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hi", fallback = SchedualServiceLoginHystric.class) //指定调用哪个服务
public interface SchedualServiceLogin {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password);
}
