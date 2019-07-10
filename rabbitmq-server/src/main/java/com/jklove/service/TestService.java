package com.jklove.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TestService implements RabbitTemplate.ReturnCallback{
    @Autowired
//    private AmqpTemplate amqpTemplate;
    private RabbitTemplate rabbitTemplate;

//    @Transactional
    public ResponseEntity send() {
        try {
            String msg = "你好，jklove ！" ;
            //参数====》 交换机 routingKey  信息
//            amqpTemplate.convertAndSend("TEST.EXCHANGE", "myKey", msg);
            log.info(">>>>>>>开始发送信息: 当前时间为: " + DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT) + "<<<<<<<");

            //设置手动ack
            rabbitTemplate.setReturnCallback(this);
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if (!ack) {
                    log.info(">>>>>>消息发送失败,原因:" + cause + correlationData.toString());
                } else {
                    log.info(">>>>>>>消息发送成功，当前时间为:"+ DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT));
                }
            });

            // 交换机 routingKey 信息
            rabbitTemplate.convertAndSend("TEST.EXCHANGE", "myKey",msg );
            return ResponseEntity.ok().build();
        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println(">>>>>>>>>接收到消费端回应，消息已被退回，未正常消费,当前时间为:"+DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT)+"<<<<<<");
    }
}
