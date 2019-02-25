//package com.example.ocdemo.controller;
//
//
//import com.example.ocdemo.service.OpenshiftService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class FabricController {
//
//    @Autowired
//    private OpenshiftService ocService;
//
//    @GetMapping(value = "/svcat")
//    public String getServiceCatalog(){
//
//        return ocService.getServiceCatalog().toString();
//    }
//
//    @RequestMapping(value = "/svcat2")
//    public String getServiceCatalog2(@RequestParam(name = "instanceName",required = true) String instanceName,
//                                     @RequestParam(name = "tplName",required = false,defaultValue = "nginx-example") String tplName){
//
//        return ocService.getServiceCatalog2(tplName,instanceName).toString();
//    }
//}
