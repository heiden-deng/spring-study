package com.heiden.cloud.eurekaclienttest2.controller;

import com.heiden.cloud.eurekaclienttest2.enties.Category;
import com.heiden.cloud.eurekaclienttest2.service.CategraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class Test {

    @Autowired
    private CategraryService categraryService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/category")
    public List<Category> category(){
        return categraryService.getCategoryList();
    }

    @GetMapping("/say")
    public String sayHello(){
        return "from service-hi:" + restTemplate.getForObject("http://service-hi/hi",String.class);
    }

    @GetMapping("/header-param")
    public String testHeader(@RequestHeader(value = "Authorization",required = false) String token){
        return "Header params=" + token;
    }
}
