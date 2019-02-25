package com.example.db.demomybatis.po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteRule {
    private String id;
    private String path;
    private String service_id;
    private String url;
    private boolean retryable;
    private boolean enabled;
    private boolean strip_prefix;
    private String api_name;
}
