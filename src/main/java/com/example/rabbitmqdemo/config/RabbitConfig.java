package com.example.rabbitmqdemo.config;



import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public final static String message = "topic.message";
    public final static String messages = "topic.messages";
    public final static String fanA = "fanout.A";
    public final static String fanB = "fanout.B";
    public final static String fanC = "fanout.C";


    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitConfig.messages);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }


    @Bean
    public Queue AMessage() {
        return new Queue(RabbitConfig.fanA);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(RabbitConfig.fanB);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(RabbitConfig.fanC);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }


    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }

}
