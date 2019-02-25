package com.example.demo.controller;

import com.example.demo.CallbackConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RestController
public class Callback {

    private static Logger logger = LoggerFactory.getLogger(Callback.class);

    @Autowired
    private RestTemplate restTemplate;

    private final static String TICKET = "ticket";

    @Autowired
    private CallbackConfig callbackConfig;

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    @ResponseBody
    public Object callback(HttpServletRequest request, HttpServletResponse response){
        System.out.println("serviceValidate=" + callbackConfig.getServicevalidate());
        Enumeration<String> params = request.getParameterNames();
        //Map<String, String> paramMap = new HashMap<String, String>();
        //paramMap.put("service",ConstantDef.APP_CALLBACK_URL);
        String targetUrl = callbackConfig.getServicevalidate();
        String ticket = "";
        logger.info("service callback");
        while (params.hasMoreElements()) {
            String paraName = params.nextElement();
            if (paraName.equalsIgnoreCase("ticket")){
                ticket = request.getParameter(paraName);
            }
            //paramMap.put(paraName, request.getParameter(paraName));
        }
        if (targetUrl.length() == 0 || ticket.length() == 0) {
            return "Invalid callback request";
        }
        String targetFullUrl = targetUrl.concat("?").concat(TICKET).concat("=").concat(ticket);
        String json = restTemplate.getForEntity(targetFullUrl,String.class).getBody();
        System.out.println(json);
        return json;
    }
}
