package com.xiaodu.testElasticJob;

import com.xiaodu.elasticJob.config.ElasticJobConfig;
import com.xiaodu.elasticJob.job.Myjob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: songshoubin
 * @date: 2019-07-16
 */
@RestController
@RequestMapping(value = "api")
public class ElasticJobController {
    @Autowired
    private ElasticJobConfig elasticJobConfig;

    @PostMapping("/addjob")
    public ResponseEntity addJob(){
        int shardingTotalCount = 2;
        elasticJobConfig.addSimpleJobScheduler(new Myjob().getClass(),"* * * * * ?",shardingTotalCount,"0=xxx,1=hhh");
        return new ResponseEntity(HttpStatus.OK);
    }
}