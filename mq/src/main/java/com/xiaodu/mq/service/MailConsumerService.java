package com.xiaodu.mq.service;

import com.xiaodu.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author: songshoubin
 * @date: 2019-07-15
 */
@Component
public class MailConsumerService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private MailService mailService;

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = "ActiveMQQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public String handleMessage(String mail) {
//        mailService.sendSimpleMail(mail,"mq测试","您在***注册了用户");
        System.out.println("成功发送邮箱" +mail);
        return "成功接受Name" + mail;
    }

}
