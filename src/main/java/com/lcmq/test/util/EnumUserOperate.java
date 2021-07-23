package com.lcmq.test.util;

import lombok.Getter;

import java.io.Serializable;

/**
 * 定义监听事件枚举类
 */
@Getter
public enum EnumUserOperate implements Serializable {
    ADD("add", 0, "新增"),
    UPDATE("update", 1, "修改"),
    DELETE("delete", 2, "删除");
    private String name;
    private Integer value;
    private String desc;

    EnumUserOperate(String name, Integer value, String desc) {
        this.name = name;
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value 获得EnumUserOperate
     */
    public static EnumUserOperate getByValue(Integer value){
        for(EnumUserOperate e : values()){
            if(e.getValue().equals(value)){
                return e;
            }
        }
        return null;
    }
}
