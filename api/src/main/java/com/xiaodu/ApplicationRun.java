package com.xiaodu;

import com.xiaodu.dao.ElaJobMapper;
import com.xiaodu.dao.TestUserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * CPS版权保护系统启动类
 *
 * @author songshoubin
 * @date 2019-5-15
 */
@SpringBootApplication
@EnableTransactionManagement
public class ApplicationRun implements CommandLineRunner {


    @Resource
    private TestUserMapper testUserMapper;

    @Resource
    private ElaJobMapper elaJobMapper;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        elasticJobService.scanAddJob();
//        taskJobMapper.selectByPrimaryKey(1L);
        System.out.println(elaJobMapper.selectByPrimaryKey(1L).toString());
        System.out.println(testUserMapper.selectByPrimaryKey(1).toString());
    }
}
