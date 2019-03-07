package com.example.stream.streamconsumer2.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
    String MYINPUT = "MyInput";

    @Input("MyInput")
    SubscribableChannel MyInput();
}
