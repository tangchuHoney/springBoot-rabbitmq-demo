//package com.jklove.listener;
//
//
//import cn.hutool.core.date.DatePattern;
//import cn.hutool.core.date.DateUtil;
//import com.rabbitmq.client.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Slf4j
//public class DeadListener {
//
////    @RabbitListener(queues = "X-Queue-dead")
////    public void dealDeadQueue(@Payload Map<String,Object> message, Channel channel) throws IOException {
////        log.info(">>>>>>>>死信队列接收到死信消息：" + message.get("order") + "当前时间为: " + DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_FORMAT) + "<<<<<<<");
////        // 声明一个消费者（关闭为支付订单的服务）
////        final Consumer consumer = new DefaultConsumer(channel){
////            @Override
////            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
////                                       byte[] body) throws IOException {
////                String message = new String(body, "UTF-8");
////                System.out.println("Received and close [" +envelope.getRoutingKey() +"]"+message);
////            }
////        };
////
////        // 消费者消费--队列()
////        /**
////         * String basicConsume​(String queue, boolean autoAck, Consumer callback) throws IOException
////         * 使用服务器生成的使用者Tag启动一个非nolocal的、非排他的使用者。
////         * 参数：
////         * queue-队列的名称
////         * autoAck-如果服务器认为消息已被发送，则为true；如果服务器期望得到明确的确认，则为false
////         * callback-与消费者对象的接口
////         * 返回：
////         * 服务器生成的使用者标签
////         * 投掷：
////         * IOException-如果遇到错误
////         */
////        channel.basicConsume("delay_order_queue",true , consumer);
////    }
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "X-Queue-dead",durable = "true"),
//            exchange = @Exchange(value ="X-Exchange-dead",ignoreDeclarationExceptions = "true"),
//            key = {"ttl"}
////            arguments = @Argument(name = "x-dead-letter-exchange",value ="X-Exchange-dead" )
//    ))
//    @RabbitHandler
//    public void listenerDelayOrder(@Payload  Map<String, Object> msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        log.info(">>>>>>>死信队列监听到一条到消息" + msg.get("order")+"<<<<<<<<<");
//        //===================关闭订单=========
//        log.info(">>>>>>>>>>>>>>>>>开始关闭订单<<<<<<<<<<<<<<");
//        log.info(">>>>>>>>>>>>>>>订单已关闭<<<<<<<<<<<<<<<<<<<<");
//        //===========服务器重启后该条消息已不存在=======
//        channel.basicAck(tag, false);
//
//    }
//}
