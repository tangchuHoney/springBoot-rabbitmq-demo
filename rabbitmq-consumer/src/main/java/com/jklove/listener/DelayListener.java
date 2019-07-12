//package com.jklove.listener;
//
//import com.rabbitmq.client.BuiltinExchangeType;
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Slf4j
//public class DelayListener {
//    //            'x-message-ttl': 10000  //延迟时间 （毫秒）
////        'x-dead-letter-exchange': exchange,  // 延迟结束后指向交换机（死信收容交换机）
////        'x-dead-letter-routing-key': queue,  //延迟结束后指向队列（死信收容队列），可直接设置queue name也可以设置routing-key
////        rabbitMQ为每个队列设置消息的超时时间。只要给队列设置x-message-ttl 参数，就设定了该队列所有消息的存活时间，
////        时间单位是毫秒。如果声明队列时指定了死信交换器，则过期消息会成为死信消息
//    @RabbitListener(
//            bindings = {
//                    @QueueBinding(
//                            value = @Queue(value = "delay_order_queue", durable = "true"),
//
//                    arguments = {
//                            @Argument(name = "x-message-ttl", value = "5 * 1000"),//设置5s后过期
//                            @Argument(name = "x-dead-letter-exchange", value = "dead-order-exchange"),
//                            @Argument(name = "x-dead-letter-routing-key", value = "dead")
//
//                    },
//                            exchange = @Exchange(value = "delay_exchange", ignoreDeclarationExceptions = "true"),
//                            key = {"delay"}
//                    ),
//                    @QueueBinding(value = @Queue(value = "dead-order-queue", durable = "true"),
//                            exchange = @Exchange(value = "dead-order-exchange", ignoreDeclarationExceptions = "true"),
//                            key = {"dead"}
//
//                    )
//
//            }
//    )
//    public void listenerDelayOrder(@Payload Map<String, Object> msg, Channel channel, Message message) throws IOException {
//        log.info(">>>>>>延迟队列监听到一条延迟消息：" + msg.get("order") + "<<<<<<<<<<");
//
////        // 声明一个死信交换机
////        String deadExchangeName = "dead-order-exchange";
////        channel.exchangeDeclare(deadExchangeName , BuiltinExchangeType.DIRECT);
//////        // 声明一个死信订单队列 ,并绑定死信交换机
////        String deadQueueName = "dead-order-queue";
////        Map<String ,Object> arguments = new HashMap<>();
////        arguments.put("x-dead-letter-exchange",deadExchangeName);
////        arguments.put("x-message-ttl", 10 * 1000);  // 5秒过期时间
////        channel.queueDeclare(deadQueueName,true , false ,false , arguments );
//////        // 绑定
////        channel.queueBind(deadQueueName, deadExchangeName ,"dead",msg);
//
//
//    }
//
//
//}
