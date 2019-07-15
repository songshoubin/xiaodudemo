package com.xiaodu.mq.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * mq配置类
 *
 * @author: songshoubin
 * @date: 2019-07-15
 */
@Configuration
@EnableJms //启动消息队列
public class BeanConfig {

    //定义存放消息的队列
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("ActiveMQQueue");
    }

    //定义存放消息的主题
    @Bean
    public Topic topic(){
        return new ActiveMQTopic("pub");
    }
}
