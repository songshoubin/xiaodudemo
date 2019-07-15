package com.xiaodu.user.service.Impl;

import com.xiaodu.user.model.TestUser;
import com.xiaodu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;

/**
 * @author: songshoubin
 * @date: 2019-07-15
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private Queue queue;
    //注入springboot封装的工具类
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestUser addUser(TestUser testUser) {
        //添加消息到消息队列(取出注册用户邮箱)
        jmsMessagingTemplate.convertAndSend(queue, testUser.getMail());
        return null;
    }
}
