package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallbackConfig {

    @Value("${iam.servicevalidate}")
    private String servicevalidate;

    public String getServicevalidate() {
        return servicevalidate;
    }

    public void setServicevalidate(String servicevalidate) {
        this.servicevalidate = servicevalidate;
    }
}
