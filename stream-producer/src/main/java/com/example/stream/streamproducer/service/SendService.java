package com.example.stream.streamproducer.service;


import com.example.stream.streamproducer.channel.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(MySource.class)
public class SendService {

    @Autowired
    private MySource source;

    public void sendMessage(String msg){
        source.myOutput().send(MessageBuilder.withPayload(msg).build());
    }
}
