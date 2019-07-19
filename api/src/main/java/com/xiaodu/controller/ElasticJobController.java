package com.xiaodu.controller;

//import com.xiaodu.elasticJob.config.ElasticJobConfig;
import com.xiaodu.job.ElasticJobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务接口
 *
 * @author: songshoubin
 * @date: 2019-07-16
 */
@RestController
@RequestMapping(value = "api")
public class ElasticJobController {

   @Autowired
   ElasticJobHandler elasticJobHandler;
    /**
     * 动态添加任务(参数包括：任务类，cron，分片总数，分片项）
     *
     * @return
     */
    @PostMapping("/addjob")
    public ResponseEntity addJob(){
//        int shardingTotalCount = 3;
//        elasticJobConfig.addSimpleJobScheduler(new Myjob().getClass(),"* * * * * ?",shardingTotalCount,"0=Beijing,1=Shanghai,2=Guangzhou");
        try {
            elasticJobHandler.addJob("myjob","0/5 * * * * ?",2,"66666","0=A,1=B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}