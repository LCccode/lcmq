package com.lcmq.test.controller;

import com.lcmq.test.producer.RabbitProducer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证mq 统一入口
 */
@Api(tags = "MQ发送消息调试接口")
@RestController
@RequestMapping("/mq")
public class RabbitMqController {
    //注入 rabbitmq 的 生产者
    @Autowired
    private RabbitProducer rabbitProducer;

    /**
     * 通过mq直接给定义的queue 发送消息
     * @return
     */
    @PostMapping("/sendDemoQueue1")
    public Object sendDemoQueue1(){
        rabbitProducer.sendDemoQueue();
        return "success";
    }

//==========================================================================

    /**
     * 通过 fanout 广播 给所有queue 发送消息
     * 全部 定义过得queue 都发送消息
     * @return
     */
    @PostMapping("/sendFanout")
    public Object sendFanout() {
        //会把消息推送给fanout 交换机
        //交换机会根据规则推送给 消息消费者
        rabbitProducer.sendFanOut();
        return "success";
    }

//==========================================================================

    @PostMapping("/sendTopicTopicAB")
    public Object sendTopicTopicAB() {
        rabbitProducer.sendTopicToAB();
        return "success";
    }

    @PostMapping("/sendTopicTopicB")
    public Object sendTopicTopicB() {
        rabbitProducer.sendTopicTopicB();
        return "success";
    }

    @PostMapping("/sendTopicTopicBC")
    public Object sendTopicTopicBC() {
        rabbitProducer.sendTopicTopicBC();
        return "success";
    }

}
