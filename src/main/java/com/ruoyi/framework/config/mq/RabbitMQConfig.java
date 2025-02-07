//package com.ruoyi.framework.config.mq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @program:
// * @ClassName:
// * @description:
// * @author: zgc
// * @date:
// * @Version 1.0
// **/
//@Configuration
//@EnableRabbit // 启用 RabbitMQ
//public class RabbitMQConfig {
//
//    // 1. 定义队列
//    @Bean
//    public Queue exampleQueue() {
//        return new Queue("exampleQueue", true); // durable=true, 确保队列持久化
//    }
//
//    // 2. 定义交换机
//    @Bean
//    public DirectExchange exampleExchange() {
//        return new DirectExchange("exampleExchange", true, false); // durable=true, autoDelete=false
//    }
//
//    // 3. 定义绑定
//    @Bean
//    public Binding exampleBinding(Queue exampleQueue, DirectExchange exampleExchange) {
//        return BindingBuilder.bind(exampleQueue).to(exampleExchange).with("exampleRoutingKey");
//    }
//
//    // 4. 配置 RabbitTemplate（消息发送支持JSON）
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter()); // 支持JSON消息格式
//        rabbitTemplate.setMandatory(true);  // 设置为强制发送
//        return rabbitTemplate;
//    }
//
//    // 5. 使用 @RabbitListener 注解监听队列消息
//    @RabbitListener(queues = "exampleQueue")
//    public void listenToExampleQueue(String message) {
//        // 处理接收到的消息
//        System.out.println("Received message: " + message);
//    }
//
//}
