package com.example.ocdemo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OCServiceTest {

    @Autowired
    private OCService ocService;

    @Test
    public void printTpl() throws Exception {
        String result = ocService.PrintTpl();
        Assert.assertTrue(result.length() != 0);
    }

}