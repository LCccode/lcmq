package com.lcmq.test.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic主题模式
 * 利用topic模式可以实现模糊匹配
 *  同样的，在RabbitConfig中配置topic队列跟交换器
 *  和广播模式 fanout不同: 注意的是这里需要多配置一个bindingKey。
 */
@Configuration
public class RabbitTopicConfig {
    //topic 主题模式
    @Bean
    public Queue topicA(){
        return new Queue("topic.aa");
    }
    @Bean
    public Queue topicB(){
        return new Queue("topic.b");
    }
    @Bean
    public Queue topicC(){
        return new Queue("topic.c");
    }

    /**
     * 定义个topic交换器
     * @return
     */
    @Bean
    TopicExchange topicExchange(){
        // 定义一个名为fanoutExchange的fanout交换器
        return new TopicExchange("topicExchange");
    }

    /**
     * 将定义的topicA队列与topicExchange交换机绑定
     * topicA的key为topic.msg 那么他只会接收包含topic.msg的消息
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWihtAA(){
        return BindingBuilder.bind(topicA()).to(topicExchange()).with("topic.dj");
    }
    /**
     * topicB的key为topic.#那么他只会接收topic开头的消息
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWihtB(){
        return BindingBuilder.bind(topicB()).to(topicExchange()).with("topic.#");
    }

    /**
     * topicC的key为topic.*.z那么他只会接收topic.x.z这样格式的消息
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWihtC(){
        return BindingBuilder.bind(topicC()).to(topicExchange()).with("topic.*.z");
    }
}
