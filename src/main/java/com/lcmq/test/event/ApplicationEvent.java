package com.lcmq.test.event;

import java.util.EventObject;

/**
 * 自定义事件类继承ApplicationEvent类，重写方法
 * 
 * 之后监听的object类都继承本类 如: UserActionEvent
 */
public abstract class ApplicationEvent extends EventObject {

    private final long timestamp = System.currentTimeMillis();
    /**
     * 继承后默认生成的方法
     * @param source
     */
    public ApplicationEvent(Object source) {
        super(source);
    }

    public final long getTimestamp(){
        return this.timestamp;
    }
}
