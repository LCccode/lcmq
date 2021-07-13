package com.lcmq.test.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class RabbitProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 直接发送给定义队列 demoQueue1
     */
    public void sendDemoQueue(){
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);

        // 第一个参数为刚刚定义的队列名称
        this.rabbitTemplate.convertAndSend("demoQueue1", dateString + "lc_test1");
    }
//==========================================================================//==========================================================================
    /**
     * 用来向fanout队列发送消息。
     */
    public void sendFanOut(){
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);

        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey 我们不用管空着就可以，第三个是你要发送的消息
        this.rabbitTemplate.convertAndSend("fanoutExchange","",dateString + "这是实验mq广播发送");
    }
//==========================================================================//==========================================================================

    /**
     * Topic主题模式
     * 利用topic模式可以实现模糊匹配
     */
    public void sendTopicToAB(){
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.msg] send msg:" + dateString;
//        log.info(dateString);
        // 注意 第一个参数是我们交换机的名称
        // 第二个参数是routerKey topic.msg
        // 第三个是你要发送的消息
        // 这条信息将会被 topic.a  topic.b接收
        this.rabbitTemplate.convertAndSend("topicExchange","topic.dj",dateString);
    }

    public void sendTopicTopicB() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.good.msg] send msg:" + dateString;
//        log.info(dateString);
        // 注意 第一个参数是我们交换机的名称
        // 第二个参数是routerKey topic.msg
        // 第三个是你要发送的消息        // 这条信息将会被topic.b接收
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.#", dateString);
    }

    public void sendTopicTopicBC() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.m.z] send msg:" + dateString;
//        log.info(dateString);
        // 注意 第一个参数是我们交换机的名称
        // 第二个参数是routerKey topic.msg
        // 第三个是你要发送的消息        // 这条信息将会被topic.b、topic.c接收
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.m.z", dateString);
    }
}
