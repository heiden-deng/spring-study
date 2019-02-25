package com.heiden.cloud.study.ribbon.controller;


import com.heiden.cloud.study.ribbon.service.HelloService;
import com.heiden.cloud.study.ribbon.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private JWTService jwtService;

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService();
    }

    @RequestMapping(value = "/login")
    public String hi(@RequestParam String name, @RequestParam String password) {
        return jwtService.loginService(name, password);
    }
}
