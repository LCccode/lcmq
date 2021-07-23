package com.lcmq.test.service.impl;

import com.lcmq.test.dto.User;
import com.lcmq.test.event.UserActionEvent;
import com.lcmq.test.service.UserService;
import com.lcmq.test.util.EnumUserOperate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 定义操作发布：ApplicationEventPublisher
 * <p>
 * 发布者会调用 ApplicationEventPublisher的publishEvent 方法对某一事件进行发布。
 * 随后Spring容器会把该事件告诉所有的监听者（我的“女神”有动态了）
 * 监听者根据拿到的“信息、某些指令或者某些数据”去做一些业务上的操作
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //定义操作发布：ApplicationEventPublisher 发布者
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public User addUser(User user) {
        // 复制对应时间event对象
        UserActionEvent userActionEvent = new UserActionEvent(this);
        userActionEvent.setUser(user);
        userActionEvent.setOperate(EnumUserOperate.ADD);
        userActionEvent.setSuccess(true);

        /**
         * 发布者发布
         *
         * 这个模式常常会与设计模式中观察者模式进行对比。举个栗子：上课铃响了，老师和同学听到铃声后
         * 都来班里了（老师要上课，学生要听课）。
         * 在这个事件里，被观察的是“铃声”，“铃声响了”是一种状态
         * 或者说是一种通知。告诉大家：该上课了
         */
        applicationEventPublisher.publishEvent(userActionEvent);
        log.info("发送add事件:{}", userActionEvent);

        return user;
    }
}
