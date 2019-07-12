package com.jklove.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.util.Map;

/**
 * ClassName TtlMessageListener
 * Description 过期队列监听器，如果启用的话，消息就算是被消费了，除非在channel中应答模式为reject，并且不再重新投回，否则不会转发到死信队列
 *
 * @Author wudy
 * @Date 2019/6/4 10:34
 * @Version 1.0
 **/
@Slf4j
//@Service
@RabbitListener(queues = "X-Queue-ttl")
public class TtlMessageListener {

    @RabbitHandler
    public void receive(@Payload Map<String,Object> message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        log.info(">>>>>>>延迟队列监听到一条到消息" + message.get("order")+"<<<<<<<<<");
        if ("reject".equals(message.get("reject"))) {
            channel.basicReject(tag,false);
        }
    }
}
