package com.jklove.listener;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * ClassName DeadMessageListener
 * Description 死信队列监听器
 *
 * @Author wudy
 * @Date 2019/6/4 11:17
 * @Version 1.0
 **/
@Slf4j
@Component
@RabbitListener(queues = "X-Queue-dead")
public class DeadMessageListener {

    @RabbitHandler
    public void receive(@Payload Map<String,Object> message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        log.info(">>>>>>>死信队列监听到一条到消息" + message.get("order")+"<<<<<<<<<");
        //===================关闭订单=========
        //正常关闭
        log.info(">>>>>>>>>>>>>>>>>开始关闭订单<<<<<<<<<<<<<<");
        log.info(">>>>>>>>>>>>>>>订单已关闭<<<<<<<<<<<<<<<<<<<<");
        //===========服务器重启后该条消息已不存在=======
        channel.basicAck(tag, false);

        //订单异常关闭  不写 channel.basicAck(tag, false);

        //拒绝订单关闭
//            channel.basicReject(tag, false);
    }
}
