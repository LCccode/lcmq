package com.lcmq.test.monitor;

import com.lcmq.test.dto.User;
import com.lcmq.test.event.UserActionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 自定义监听必须交给spring容器管理，否则不起作用哈
 *
 * 下面定义了两种，一种是根据el 去筛选相关监听
 * 一种是全量监听，只根据入参的class 去区分
 *
 * 下面配置了两种，所以请求add 操作，会打印两次业务逻辑操作
 *  请求update 会打印第二个方法的业务逻辑操作
 */
@Component
@Slf4j
public class UserMonitor {
    /**
     * 监听新增用户时间
     * 异步操作,使用自定义线程池 -- lazyTraceExecutor
     * EventListener中细化监听具体某种事件
     *
     * 使用表达式condition = "#event.operate.name()==‘ADD’"对监听进行了细化：
     * 监听类型为“新增”的事件
     */
    @Async("lazyTraceExecutor")
    @EventListener(value = UserActionEvent.class,condition = "#event.operate.name()=='ADD'")
    public UserActionEvent addUserApplicationEvent(UserActionEvent event){
        try {
            User user = event.getUser();
            log.info("监听到新增用户:{}",user);
            //业务的自定义处理位置!
            log.info("lc做出了业务逻辑处理!");
        } catch (Exception e) {
            log.error("事件:{},执行异常:{}",event,e.getMessage());
            return event;
        }
        return null;

    }

    /**
     * 实验只根据监听的监听类来监听
     * 本处可以只开起这个方法， 就可以监听所有发布者关于 UserActionEvent 的操作
     * 不配置condition
     * @param event
     * @return
     */
    @Async("lazyTraceExecutor")
    @TransactionalEventListener
    public UserActionEvent userApplicationEvent(UserActionEvent event){
        try {
            User user = event.getUser();
            log.info("监听到操作用户:{}",user);
            //业务的自定义处理位置!
            log.info("lc做出了业务逻辑处理!");
        } catch (Exception e) {
            log.error("事件:{},执行异常:{}",event,e.getMessage());
            return event;
        }
        return null;

    }
}
