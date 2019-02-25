package com.example.rabbitmqdemo.controller;

import com.example.rabbitmqdemo.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Autowired
    private RabbitMQService rabbitMQService;


    @GetMapping(value = "/send1")
    public String testSend(@RequestParam(name="msg") String msg) {
        rabbitMQService.send(msg);
        return "success";
    }
}
