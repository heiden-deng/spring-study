package com.example.stream.streamproducer.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {
    @Output("MyOutput")
    MessageChannel myOutput();
}
