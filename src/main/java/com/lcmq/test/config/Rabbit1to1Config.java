package com.lcmq.test.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 普通队列模式
 * 新建一个RabbitMQ配置类，并添加一个demoQueue队列。
 */
@Configuration
public class Rabbit1to1Config {

    /**
     * 定义demoQueue1队列
     */
    @Bean
    public Queue demoString() {
        return new Queue("demoQueue1");
    }
}
