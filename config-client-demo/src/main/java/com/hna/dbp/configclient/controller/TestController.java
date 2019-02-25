package com.hna.dbp.configclient.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//测试读取配置中心配置文件的某个属性值

@RestController
public class TestController {
    @Value("${spring.datasource.username}")
    String username;

    @RequestMapping(value = "/hi")
    public String hi(){
        return username;
    }
}
