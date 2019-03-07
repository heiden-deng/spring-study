package com.example.stream.streamconsumer2.service;

import com.example.stream.streamconsumer2.channel.MySink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(MySink.class)
public class RecieveService {

    @StreamListener(MySink.MYINPUT)
    public void receive(Object payload){
        System.out.println(payload);
    }

}
