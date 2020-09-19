package com.xgit.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * Created by tianxuanxuan
 * On 2020-09-19 15:17
 */
@Slf4j
@EnableBinding(Sink.class) //消费者，因此绑定sink.class
public class ReceiverMessageListenerController {
    @Value("${server.port}")
    private String serverPost;

    @StreamListener(Sink.INPUT)
    public void receive(Message<String> message){
        log.info("消费者1号， -----> 接收到消息：" + message.getPayload() +
                "\t port" + serverPost);
    }
}
