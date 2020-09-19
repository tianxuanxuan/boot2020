package com.xgit.boot.service.impl;

import com.xgit.boot.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by tianxuanxuan
 * On 2020-09-19 14:36
 */
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements IMessageProvider {
    @Resource(name = "output") //为什么这里注入的时候需要name
    private MessageChannel outPut; //消息发送通道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        outPut.send(MessageBuilder.withPayload(serial).build());
        log.info("*****serial: "  +serial);
        return serial;
    }
}
