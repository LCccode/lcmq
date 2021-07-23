package com.lcmq.test.monitor;

import com.lcmq.test.dto.User;
import com.lcmq.test.event.UserActionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 自定义监听必须交给spring容器管理，否则不起作用哈
 */
@Component
@Slf4j
public class UserMonitor {
    /**
     * 监听新增用户时间
     * 异步操作,使用自定义线程池
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
}
