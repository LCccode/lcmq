package com.lcmq.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于测试 eventLisnter 注解
 * SpringBoot 中发布ApplicationEventPublisher
 * 监听ApplicationEvent 异步操作
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Integer sex;
    private Integer age;
    private String phone;
    private String email;
    private String address;

}
