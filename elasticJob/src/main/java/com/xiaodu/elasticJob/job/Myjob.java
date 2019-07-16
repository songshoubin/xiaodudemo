package com.xiaodu.elasticJob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author: songshoubin
 * @date: 2019-07-16
 */
@ElasticSimpleJob(cron = "0/5 * * * * ?", jobName = "test123", shardingTotalCount = 2, jobParameter = "测试参数", shardingItemParameters = "0=A,1=B")
@Component
public class Myjob implements SimpleJob {
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
    }
}
