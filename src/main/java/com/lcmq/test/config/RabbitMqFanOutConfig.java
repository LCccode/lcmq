package com.lcmq.test.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout广播模式
 * Fanout其实就是广播模式，只要跟它绑定的队列都会通知并且接受到消息
 * 在代码中我们配置了三个队列名、一个fanout交换机
 * 并且将这三个队列绑定到了fanout交换器上。
 * 只要我们往这个交换机生产新的消息，那么这三个队列都会收到。
 */
@Configuration
public class RabbitMqFanOutConfig {

    //=================== fanout广播模式  ====================
    @Bean
    public Queue fanoutA(){
        return new Queue("fanout.a");
    }
    @Bean
    public Queue fanoutB(){
        return new Queue("fanout.b");
    }
    @Bean
    public Queue fanoutC(){
        return new Queue("fanout.c");
    }

    /**
     * 定义个fanout交换器
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange(){
        // 定义一个名为fanoutExchange的fanout交换器
        return new FanoutExchange("fanoutExchange");
    }
    /**
     * 将定义的fanoutA B C 三个队列与fanoutExchange交换机绑定
     * @return
     */
    @Bean
    public Binding bindingExchangeWithA(){
        return BindingBuilder.bind(fanoutA()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeWithB(){
        return BindingBuilder.bind(fanoutB()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchangeWithC(){
        return BindingBuilder.bind(fanoutC()).to(fanoutExchange());
    }

}
