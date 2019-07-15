package com.xiaodu.sms.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云oss配置类
 *
 * @author songshoubin
 * @date 2019-7-15
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunSMSConfig {

    /* 短信API产品名称（短信产品名固定，无需修改） */
    private String product = "Dysmsapi";

    /* 短信API产品域名，接口地址固定，无需修改 */
    private String domain = "dysmsapi.aliyuncs.com";

    private String accessKeyId;
    private String accessKeySecret;

}
