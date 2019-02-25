package com.example.jpa.demojpa.controller;


import com.example.jpa.demojpa.entity.User;
import com.example.jpa.demojpa.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jpa")
public class JpaUserController {
    @Autowired
    private JpaUserRepository jpaUserRepository = null;

    @RequestMapping("/user")
    @ResponseBody
    public User getUser(Long id){
        User user = jpaUserRepository.findById(id).get();
        return user;
    }
}
