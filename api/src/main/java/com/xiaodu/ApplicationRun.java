package com.xiaodu;

import com.xiaodu.dao.ElaJobMapper;
import com.xiaodu.dao.TestUserMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * 系统启动类
 *
 * @author songshoubin
 * @date 2019-5-15
 */
@SpringBootApplication
@EnableTransactionManagement
public class ApplicationRun implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
