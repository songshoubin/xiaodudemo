package com.xiaodu.elasticJob.job;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import com.xiaodu.elasticJob.dao.TaskJobMapper;
import com.xiaodu.elasticJob.model.TaskJob;

import javax.annotation.Resource;
/**
 * 实现分布式任务监听器
 * 如果任务有分片，分布式监听器会在总的任务开始前执行一次，结束时执行一次
 *
 * @author: songshoubin
 * @date: 2019-07-17
 */

public class ElasticJobListener extends AbstractDistributeOnceElasticJobListener {
    @Resource
    private TaskJobMapper taskJobMapper;

    public ElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        //任务执行完成后更新状态为已执行
        TaskJob taskJob = taskJobMapper.selectByPrimaryKey(Long.valueOf(shardingContexts.getJobParameter()));
        taskJob.setStatus(1);
        taskJobMapper.insert(taskJob);
    }
}