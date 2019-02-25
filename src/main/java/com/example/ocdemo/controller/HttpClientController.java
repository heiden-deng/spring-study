package com.example.ocdemo.controller;

//import com.example.ocdemo.utils.HttpClientUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpClientController {

    /*public static final String OPENSHIFT_SERVER = "https://openshift-1:8443";
    @GetMapping(value="/test/connect")
    public String getinfo(){
        String templateurl = OPENSHIFT_SERVER + "/oapi/v1/namespaces/openshift/templates";
        return HttpClientUtil.sendHttpGet(templateurl);
    }*/
}
