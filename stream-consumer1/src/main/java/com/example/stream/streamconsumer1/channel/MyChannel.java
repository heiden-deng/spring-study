package com.example.stream.streamconsumer1.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyChannel {
    String MYINPUT = "MyInput";

    @Input("MyInput")
    SubscribableChannel MyInput();

    String MYOUTPUT = "MyOutput";

    @Output("MyOutput")
    MessageChannel MyOutput();
}
