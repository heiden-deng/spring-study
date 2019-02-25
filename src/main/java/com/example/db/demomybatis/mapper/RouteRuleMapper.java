package com.example.db.demomybatis.mapper;

import com.example.db.demomybatis.po.RouteRule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouteRuleMapper {

    @Select("select * from gateway_api_define")
    List<RouteRule> findAll();

    @Insert("insert into gateway_api_define(id,path,service_id,url,retryable,enabled,strip_prefix,api_name) values(#{id},#{path},#{service_id},#{url},#{retryable},#{enabled},#{strip_prefix},#{api_name})")
    void insert(RouteRule routeRule);
}
