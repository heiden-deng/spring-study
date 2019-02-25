package com.heiden.cloud.eurekaclienttest2.service;

import com.heiden.cloud.eurekaclienttest2.enties.Category;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value = "CLOUDKNOWLEDGE")
public interface CategraryService {
    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    public List<Category> getCategoryList();
}
