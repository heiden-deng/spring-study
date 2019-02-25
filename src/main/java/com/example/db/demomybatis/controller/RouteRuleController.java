package com.example.db.demomybatis.controller;

import com.example.db.demomybatis.po.RouteRule;
import com.example.db.demomybatis.service.RouteRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteRuleController {

    @Autowired
    private RouteRuleService routeRuleService;

    @RequestMapping("/routerules")
    @ResponseBody
    public List<RouteRule> findAll(){
        return routeRuleService.findAll();
    }
}
