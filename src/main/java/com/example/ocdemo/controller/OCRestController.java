package com.example.ocdemo.controller;


import com.example.ocdemo.service.OCService;
import com.openshift.restclient.ClientBuilder;
import com.openshift.restclient.IClient;
import com.openshift.restclient.IResourceFactory;
import com.openshift.restclient.ResourceKind;
import com.openshift.restclient.model.IPod;
import com.openshift.restclient.model.IResource;
import com.openshift.restclient.model.IService;
import com.openshift.restclient.model.template.IParameter;
import com.openshift.restclient.model.template.ITemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class OCRestController {


    @Autowired
    private OCService ocService;

    @GetMapping(value = "/test/pods")
    public String PrintPods(){
//        StringBuilder sb = new StringBuilder();
//        IClient client = new ClientBuilder("https://openshift-1:8443")
//                .withUserName("jq.deng")
//                .withPassword("123456")
//                .build();
//        IResourceFactory rf = client.getResourceFactory();
////        IService service = rf.stub(ResourceKind.SERVICE,"test","test");
////
////        List<IPod> pods = service.getPods();
////        for (IPod pod : pods){
////            sb.append(pod.getHost() + ":" + pod.getIP() + " : " + pod.getName() + '\n');
////        }
//
//        List<ITemplate> templates = client.list(ResourceKind.TEMPLATE,"openshift");
//        for (ITemplate template : templates){
//            System.out.println(template.toJson());
//        }
//
//        System.out.println("template ................");
//        ITemplate template = rf.stub(ResourceKind.TEMPLATE,"nginx-example","openshift");
//
//        //System.out.println(template.getName() + ",meta: " + template.getMetadata().toString());
////        sb.append("template ................" + template.getObjects().toString());
////        Collection<IResource> resources = template.getObjects();
////		for (IResource resource : resources) {
////			System.out.println(resource.getName() + ":" + resource.getKind());
////		}
//        System.out.println(template.toJson());
//        Map<String,IParameter> parameters = template.getParameters();
//        for(String key : parameters.keySet()) {
//            System.out.println("  key=" + key + ",value=" + parameters.get(key).getValue());
//        }
        ocService.PrintTpl();
        return "success";
    }
}
