package com.heiden.cloud.study.feign.service;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceLoginHystric implements SchedualServiceLogin{
    @Override
    public String login(String name, String password) {
        return "sorry, server has a error!";
    }
}
