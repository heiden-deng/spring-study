package com.iam.test.demo.controller;

import com.iam.test.demo.config.CallBackConfiguration;
import com.iam.test.demo.config.ConstantDef;
import com.jingantech.ngiam.Format;
import com.jingantech.ngiam.cas.CasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jingantech.ngiam.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CallBackConfiguration callBackConfiguration;

    //@Value("${iam.callbackurl}")
    //private String callbackUrl;

    private Map<String,ArrayList<String>> tokens = new HashMap<String, ArrayList<String>>();
    private final static String PARAMS = "params";
    private final static String TICKET = "ticket";

    @RequestMapping(value = "/parse", method = RequestMethod.GET)
    public String ParseTiket(@RequestParam(value = "ticket") String ticket) {
        StringBuilder sb = new StringBuilder();
        ResponseBody res = null;
        try {
            res = CasService.casValidate(ConstantDef.IAM_VALID_URL, ticket, ConstantDef.APP_CALLBACK_URL, Format.json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("用户名：" + res.toString());
        sb.append("+++++++++++用户名：").append(res.getUser()).append(";attributes=").append(res.getAttributes().toString());
        return sb.toString();
    }


    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    @org.springframework.web.bind.annotation.ResponseBody
    public Object callback(HttpServletRequest request, HttpServletResponse response) {
        String reqURL = request.getRequestURI();

        String targetUrl = "";
        String ticket = "";
        Enumeration<String> params = request.getParameterNames();
        //Map<String, String> paramMap = new HashMap<String, String>();
        //paramMap.put("service",ConstantDef.APP_CALLBACK_URL);
        logger.info("iam callback");
        while (params.hasMoreElements()) {
            String paraName = params.nextElement();
            if (paraName.equalsIgnoreCase(PARAMS)){
                targetUrl = request.getParameter(paraName);
                logger.info("target callback:" + targetUrl);
            }
            if (paraName.equalsIgnoreCase(TICKET)){
                ticket = request.getParameter(paraName);
                logger.info("ticket :" + ticket);
            }
            //paramMap.put(paraName, request.getParameter(paraName));
        }
        if (targetUrl.length() == 0 || ticket.length() == 0) {
            return "Invalid callback request";
        }
        String targetFullUrl = targetUrl.concat("?").concat(TICKET).concat("=").concat(ticket);
        try {
            response.sendRedirect(targetFullUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(json);
        return "";
    }

    @RequestMapping(value = "/serviceValidate", method = RequestMethod.GET)
    @org.springframework.web.bind.annotation.ResponseBody
    public Object serviceValidate(HttpServletRequest request, HttpServletResponse response) {
        String queryString = request.getQueryString();
        logger.info("query param:" + queryString);
        String targetIP = ConstantDef.IAM_VALID_URL;
        String ticket = "";
        String format = "json";

        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paraName = params.nextElement();
            if (paraName.equalsIgnoreCase(TICKET)){
                ticket = request.getParameter(paraName);
                logger.info("ticket :" + ticket);
            }
            if (paraName.equalsIgnoreCase("format")){
                format = request.getParameter(paraName);
                logger.info("ticket :" + ticket);
            }
            //paramMap.put(paraName, request.getParameter(paraName));
        }
        if (ticket.length() == 0) {
            return "Invalid serviceInvalid request";
        }

        String targetURL = targetIP.concat("?").concat(TICKET).concat("=").concat(ticket)
                .concat("&format=").concat(format)
                .concat("&service=").concat(URLEncoder.encode(callBackConfiguration.getCallback_url()));
        logger.info("targetURL:" + targetURL);
        String json = restTemplate.getForEntity(targetURL, String.class).getBody();
        System.out.println(json);
        return json;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @org.springframework.web.bind.annotation.ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response) {
        String reqURL = request.getRequestURI();
        String queryString = request.getQueryString();
        //String orgURI = (String) request.getAttribute("requestURI");
        logger.info("reqURL:" + reqURL);
        //logger.info("queryString:" + queryString);
        //logger.debug("orgURI:"+orgURI);
        String targetIP = ConstantDef.IAM_LOGIN_VALID_URL;
        String targetURL = targetIP.concat("?service=").concat(URLEncoder.encode(callBackConfiguration.getCallback_url()));
        logger.info("targetURL:" + targetURL);


        Enumeration<String> params = request.getParameterNames();
        //Map<String, String> paramMap = new HashMap<String, String>();
        //paramMap.put("service",ConstantDef.APP_CALLBACK_URL);
        while (params.hasMoreElements()) {
            String paraName = params.nextElement();
            if (paraName.equalsIgnoreCase("service")){
                targetURL = targetURL.concat("&").concat(PARAMS).concat("=").concat(request.getParameter(paraName));
                //paramMap.put(APPNAME, request.getParameter(paraName));
                break;
            }
            //paramMap.put(paraName, request.getParameter(paraName));
        }

        try {
            response.sendRedirect(targetURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String json = restTemplate.getForEntity(targetURL, String.class).getBody();
        //System.out.println(json);
        return "";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @org.springframework.web.bind.annotation.ResponseBody
    public Object logout(HttpServletRequest request, HttpServletResponse response){

        String targetURL = "";
        Enumeration<String> params = request.getParameterNames();
        //Map<String, String> paramMap = new HashMap<String, String>();
        //paramMap.put("service",ConstantDef.APP_CALLBACK_URL);
        while (params.hasMoreElements()) {
            String paraName = params.nextElement();
            if (paraName.equalsIgnoreCase("backUrl")){
                targetURL = request.getParameter(URLDecoder.decode(paraName));
                //paramMap.put(APPNAME, request.getParameter(paraName));
                break;
            }
            //paramMap.put(paraName, request.getParameter(paraName));
        }

        try {
            response.sendRedirect(targetURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }



}
