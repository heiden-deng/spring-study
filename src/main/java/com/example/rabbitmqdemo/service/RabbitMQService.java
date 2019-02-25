package com.example.rabbitmqdemo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class RabbitMQService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        String context = "[" + new Date() +  "]" + msg;
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

    public void send1(String msg) {
        String context = "hi, i am message 1," + msg;
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    public void send2(String msg) {
        String context = "hi, i am messages 2," + msg;
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }

    public void sendFanout(String msg) {
        String context = "hi, fanout  " + msg;
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
    }

}
