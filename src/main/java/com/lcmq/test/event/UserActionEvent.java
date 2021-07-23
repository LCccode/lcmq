package com.lcmq.test.event;

import com.lcmq.test.dto.User;
import com.lcmq.test.util.EnumUserOperate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserActionEvent extends ApplicationEvent{
    // 操作是否成功
    private Boolean success;

    //操作类型
    private EnumUserOperate operate;
    //数据对象
    private User user;
    /**
     * 继承后默认生成的方法
     *
     * @param source
     */
    public UserActionEvent(Object source) {
        super(source);
    }
}
