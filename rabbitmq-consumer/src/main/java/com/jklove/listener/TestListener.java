package com.jklove.listener;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class TestListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "TEST.EXCHANGE", durable = "true"),
            exchange = @Exchange(value = "TEST.EXCHANGE", ignoreDeclarationExceptions = "true"),
            key = {"myKey"}
    ))
    public void listener(String msg, Channel channel,Message message)  {


        log.info(">>>>>>>监听到了一条信息: " + msg + "当前时间为: " + DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT) + "<<<<<<<<");
        //=================把以下代码注释掉 就可以模拟消息的异常   消息未被手动确认  在队列里面
        // 重启该微服务 那条未被确认的消息会被重新监听到
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            //
            ////消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            ////ack返回false，并重新回到队列，api里面解释得很清楚
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info(">>>>>>>>>>>>receiver success<<<<<<<<<");
        } catch (IOException e) {
            log.info(">>>>>>>>>>>>>>receiver failed<<<<<<<<<<<");
        }
    }
}
