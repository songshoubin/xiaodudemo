package com.xiaodu.elasticJob.service;

import com.xiaodu.elasticJob.dao.TaskJobMapper;
import com.xiaodu.elasticJob.job.ElasticJobHandler;
import com.xiaodu.elasticJob.model.TaskJob;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: songshoubin
 * @date: 2019-07-17
 */
@Service
public class ElasticJobService {
    @Resource
    private ElasticJobHandler jobHandler;
    @Resource
    private TaskJobMapper taskJobMapper;

    /**
     * 扫描db，并添加任务
     */
    public void scanAddJob() {
        List<TaskJob> taskJobList = taskJobMapper.findByStatus(0);
        taskJobList.forEach(taskJob -> {
            Long current = System.currentTimeMillis();
            String jobName = "job" + taskJob.getSentTime();
            String cron;
            //说明消费未发送，但是已经过了消息的发送时间，调整时间继续执行任务
            if (taskJob.getSentTime() < current) {
                //设置为一分钟之后执行，把Date转换为cron表达式
//                cron = CronUtils.getCron(new Date(current + 60000));
                cron = "0/5 * * * * ?";
            } else {
//                cron = CronUtils.getCron(new Date(taskJob.getSentTime()));
                cron = "0/5 * * * * ?";
            }
            jobHandler.addJob(jobName, cron, 1, String.valueOf(taskJob.getId()));
        });
    }
}
