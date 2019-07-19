package com.xiaodu.controller;

import com.xiaodu.model.TestUser;
import com.xiaodu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮件发送接口
 *
 * @author: songshoubin
 * @date: 2019-07-12
 */
@RestController
@RequestMapping(value = "api")
public class MailController {

    @Autowired
    private UserService userService;

    /**
     * 用户创建成功后发送邮箱
     *
     * @param testUser
     * @return
     */
    @PostMapping(value = "/users")
    public ResponseEntity addUser(@RequestBody TestUser testUser){



        //调用业务层执行业务  应该在注册用户service层调用mq
        userService.addUser(testUser);
        return new ResponseEntity("用户创建成功",HttpStatus.OK);
    }
}
