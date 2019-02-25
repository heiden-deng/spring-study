package com.heiden.cloud.study.feign.controller;


import com.heiden.cloud.study.feign.service.SchedualServiceHi;
import com.heiden.cloud.study.feign.service.SchedualServiceLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @Autowired
    private SchedualServiceLogin schedualServiceLogin;

    @RequestMapping(value = "/hi")
    public String sayHi(@RequestParam(value = "name") String name) {
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @RequestMapping(value = "/login")
    public String sayHi(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password) {
        return schedualServiceLogin.login(name, password);
    }
}
