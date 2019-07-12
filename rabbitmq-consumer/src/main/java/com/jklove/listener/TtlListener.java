//package com.jklove.listener;
//
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 过期队列监听器，如果启用的话，消息就算是被消费了，
// * 除非在channel中应答模式为reject，并且不再重新投回，否则不会转发到死信队列
// **/
//@Component
//@Slf4j
//public class TtlListener {
//
//    @RabbitListener(bindings = @QueueBinding(ignoreDeclarationExceptions = "true",
//            value = @Queue(
//                    value = "X-Queue-ttl",
//                    durable = "true",
//                    ignoreDeclarationExceptions = "true"
//            ),
//            exchange = @Exchange(value = "X-Exchange-ttl", ignoreDeclarationExceptions = "true"),
//            key = {"ttl"},
//            arguments = {
//                    @Argument(name = "x-message-ttl", value = "5000"),//设置5s后过期
//                    @Argument(name = "x-dead-letter-exchange", value = "X-Exchange-dead"),
//                    @Argument(name = "x-dead-letter-routing-key", value = "ttl")
//            }
//    ))
//    public void receive(@Payload Map<String, Object> message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        log.info(">>>>>>>延迟队列监听到一条到消息" + message.get("order") + "<<<<<<<<<");
//        /**
//         * 如何让消息到达死信队列
//         * 1.消息被拒绝（Basic.Reject或Basic.Nack）并且设置 requeue 参数的值为 false
//         * 2.消息过期了
//         * 3.队列达到最大的长度
//         */
//
//        if ("reject".equals(message.get("reject"))) {
//            //应答模式为拒绝，并且不再投回到channel
//            channel.basicReject(tag, false);
//        }
//    }
//}
