package com.example.db.demomybatis.service;

import com.example.db.demomybatis.mapper.RouteRuleMapper;
import com.example.db.demomybatis.po.RouteRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteRuleService {

    @Autowired
    private RouteRuleMapper routeRuleMapper;

    public List<RouteRule> findAll(){
        return routeRuleMapper.findAll();
    }

    public void insertRouteRule(RouteRule routeRule){
        routeRuleMapper.insert(routeRule);
    }
}
