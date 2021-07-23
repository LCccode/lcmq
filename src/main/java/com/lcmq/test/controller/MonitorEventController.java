package com.lcmq.test.controller;

import com.lcmq.test.dto.Result;
import com.lcmq.test.dto.User;
import com.lcmq.test.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "spring事件监听模拟发起调试接口")
@RestController
@RequestMapping("/monitor")
public class MonitorEventController {

    @Autowired
    private UserService userService;

    @PostMapping("/userAdd")
    public Result<Object> addUser(@RequestBody User user){
        userService.addUser(user);
        return new Result<>();
    }
    @PostMapping("/userUpdate")
    public Result<Object> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new Result<>();
    }

}
