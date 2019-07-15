package com.xiaodu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * CPS版权保护系统启动类
 *
 * @author songshoubin
 * @date 2019-5-15
 */
@SpringBootApplication
@EnableTransactionManagement
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }
}
