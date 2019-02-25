//package com.example.ocdemo.service;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
//import io.fabric8.kubernetes.api.model.Secret;
//import io.fabric8.kubernetes.api.model.ServiceList;
//import io.fabric8.kubernetes.client.Config;
//
//import io.fabric8.openshift.api.model.Parameter;
//import io.fabric8.openshift.api.model.Template;
//import io.fabric8.openshift.api.model.TemplateList;
//import io.fabric8.openshift.client.DefaultOpenShiftClient;
//import io.fabric8.openshift.client.OpenShiftClient;
//import io.fabric8.openshift.client.OpenShiftConfigBuilder;
//import me.snowdrop.servicecatalog.api.client.DefaultServiceCatalogClient;
//import me.snowdrop.servicecatalog.api.client.ServiceCatalogClient;
//import me.snowdrop.servicecatalog.api.model.*;
//import org.springframework.stereotype.Service;
//
////import javax.crypto.SecretKey;
//import java.io.UnsupportedEncodingException;
//import java.util.*;
//
//
//@Service
//public class OpenshiftService {
//
//    private static Config config;
//    private static OpenShiftClient client;
//    private static ServiceCatalogClient sc;
//    private final static Base64.Encoder encoder = Base64.getEncoder();
//
//    static{
//        config = new OpenShiftConfigBuilder().withMasterUrl("https://openshift-1:8443").withUsername("jq.deng")
//                .withPassword("123456").withNamespace("openshift").build();
//        client = new DefaultOpenShiftClient(config);
//        sc = client.adapt(ServiceCatalogClient.class);
//        //sc = new DefaultServiceCatalogClient(config);
//    }
//
//    public List<String> getServiceCatalog(){
//        List<String> svc = new ArrayList<>();
//        /*ServiceList myServices = client.services().list();
//        List<io.fabric8.kubernetes.api.model.Service> services = myServices.getItems();
//        for(io.fabric8.kubernetes.api.model.Service service : services){
//            svc.add(service.getMetadata().getName());
//        }*/
//
//
//        //client.templates().inNamespace("openshift").list()
//
//
//        TemplateList templateList = client.templates().inNamespace("openshift").list();
//        List<Template> templates = templateList.getItems();
//        for (Template template : templates){
//            svc.add(template.getMetadata().getName());
//        }
//        return svc;
//    }
//
//    public List<String> getServiceCatalog2(String tplName,String instanceName){
//        List<String> svc = new ArrayList<>();
//
//        ClusterServiceClassList list = sc.clusterServiceClasses().list();
//        List<ClusterServiceClass> clusterServiceClasses = list.getItems();
//        for (ClusterServiceClass clusterServiceClass : clusterServiceClasses){
//            svc.add(clusterServiceClass.getSpec().getExternalName());
//        }
//
//        ClusterServiceClassList list2 = sc.clusterServiceClasses().list();
//        list2.getItems().stream()
//                .map(b -> b.getSpec().getClusterServiceBrokerName() + " " + b.getSpec().getExternalName())
//                .forEach(System.out::println);
//
//        ClusterServiceBrokerList list3 = sc.clusterServiceBrokers().list();
//        list3.getItems().stream()
//                .map(b->b.getMetadata().getName())
//                .forEach(System.out::println);
//
//        ClusterServicePlanList list4 = sc.clusterServicePlans().list();
//        list4.getItems().stream()
//                .map(b -> b.getSpec().getClusterServiceBrokerName() +
//                        " " +
//                        b.getSpec().getExternalName() +
//                        " " +
//                        b.getSpec().getClusterServiceClassRef())
//                                .forEach(System.out::println);
//
//
//        /*Secret secret = client.secrets().inNamespace("test").createNew().withNewMetadata()
//               .withName("secret-" + instanceName)
//               .withNamespace("test")
//               .endMetadata()
//               .addToStringData("NAME",instanceName)
//               .done();*/
//
//        Optional<Template> templateOptional = client.templates().inNamespace("openshift").list().getItems().stream().filter(t -> t.getMetadata().getName().equalsIgnoreCase(tplName)).findFirst();
//        if (templateOptional.isPresent()) {
//            Template template = templateOptional.get();
//            List<Parameter> parameters = template.getParameters();
//            System.out.println(template.getMetadata().getName() + "   paramenters:");
//            Map<String,String> tplParams = new HashMap<>();
//            for (Parameter parameter : parameters){
//                if ((parameter.getRequired() != null && parameter.getRequired().booleanValue())  && parameter.getValue() != null){
//                    tplParams.put(parameter.getName(),parameter.getValue());
//                }
//                System.out.println("      " + parameter.getName() + ",displayName=" + parameter.getDisplayName() +",Value: " + parameter.getValue());
//            }
//            tplParams.put("NAME",instanceName);
//            String param = JSON.toJSONString(tplParams);
//            System.out.println("param:" + param);
//            //String param = "{\"MEMORY_LIMIT\":\"512Mi\",\"NAME\":\"" + instanceName + "\",\"NAMESPACE\":\"openshift\",\"NGINX_VERSION\":\"1.12\",\"SOURCE_REPOSITORY_URL\":\"https://github.com/sclorg/nginx-ex.git\"}";
//            String secretParam = null;
//            try {
//                secretParam = encoder.encodeToString(param.getBytes("UTF-8"));
//                System.out.println("parameters:" + secretParam);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            //String secretParam = "eyJNRU1PUllfTElNSVQiOiI1MTJNaSIsIk5BTUUiOiJuZ2lueC1leGFtcGxlLTEwMTIiLCJOQU1FU1BBQ0UiOiJvcGVuc2hpZnQiLCJOR0lOWF9WRVJTSU9OIjoiMS4xMiIsIlNPVVJDRV9SRVBPU0lUT1JZX1VSTCI6Imh0dHBzOi8vZ2l0aHViLmNvbS9zY2xvcmcvbmdpbngtZXguZ2l0In0=";
//            Secret secret = client.secrets().inNamespace("test").createNew().withNewMetadata()
//                    .withName("secret-" + instanceName)
//                    .withNamespace("test")
//                    .endMetadata()
//                    .addToData("parameters",secretParam)
//                    .done();
//
//            SecretKeyReference skr = new SecretKeyReference("parameters","secret-" + instanceName);
//            ParametersFromSource parametersFromSource = new ParametersFromSource();
//            parametersFromSource.setSecretKeyRef(skr);
//
//            //create
//            ServiceInstance si = sc.serviceInstances().inNamespace("test").createNew()
//                    .withNewMetadata()
//                    .withName(instanceName)
//                    .endMetadata()
//                    .withNewSpec()
//                    //.withClusterServiceClassName("1bb530e7-78ec-11e8-8a64-fa163ea01e2c")
//                    .withClusterServiceClassExternalName(tplName)
//                    .withClusterServicePlanExternalName("default")
//                    .withParametersFrom(parametersFromSource)
//                    .endSpec()
//                    .done();
//            System.out.println(si.toString());
//        }
//
//
//        return svc;
//    }
//
//}
