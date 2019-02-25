package com.iam.test.demo.config;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.context.annotation.PropertySource;


//@PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true)
@Configuration
public class CallBackConfiguration {

    @Value("${iamtest.callbackurl:http://nebula.hnagroup.com/callback}")
    private String callbackurl;

    public String getCallback_url() {
        return callbackurl;
    }

    public void setCallback_url(String callback_url) {
        this.callbackurl = callback_url;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
