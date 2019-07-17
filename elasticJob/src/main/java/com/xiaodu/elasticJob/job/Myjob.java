package com.xiaodu.elasticJob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import com.xiaodu.user.dao.TestUserMapper;
import com.xiaodu.user.model.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 简单任务类
 *
 * @author: songshoubin
 * @date: 2019-07-16
 */
@ElasticSimpleJob(cron = "0/5 * * * * ?", jobName = "test123", shardingTotalCount = 3, jobParameter = "测试参数", shardingItemParameters = "0=Beijing,1=Shanghai,2=Guangzhou")
@Component
public class Myjob implements SimpleJob {
    @Autowired
    TestUserMapper testUserMapper;

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s.当前参数: %s," +
                        "当前任务名称: %s.当前任务参数: %s",
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()));
        List<TestUser> testUserList = testUserMapper.findUser(shardingContext.getShardingTotalCount(),shardingContext.getShardingItem());
        if(testUserList != null)
            for (TestUser testUser:testUserList
                 ) {
                System.out.println(testUser.toString());
            }
    }
}
