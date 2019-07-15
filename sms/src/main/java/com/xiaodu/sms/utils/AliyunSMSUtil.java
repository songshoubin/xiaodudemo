package com.xiaodu.sms.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.xiaodu.cache.constant.Constant;
import com.xiaodu.cache.service.RedisService;
import com.xiaodu.cache.vo.RedisVo;
import com.xiaodu.sms.config.AliyunSMSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 阿里云SMS工具类
 *
 * @author songshoubin
 * @date 2019-7-15
 */
@Slf4j
@Component
public class AliyunSMSUtil {

    @Autowired
    private AliyunSMSConfig aliyunSMSConfig;

    @Autowired
    private RedisService redisService;

    /**
     * 发送短信接口
     *
     * @param mobile
     * @param signName      模板签名
     * @param templateCode  模板代码
     * @param templateParam 模板替换参数
     * @param outId         提供给业务方扩展字段
     * @return boolean true表示发送成功，false表示发送失败
     * @throws ClientException
     */
    public boolean sendSms(String mobile, String signName, String templateCode, String templateParam, String outId) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunSMSConfig.getAccessKeyId(), aliyunSMSConfig.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", aliyunSMSConfig.getProduct(), aliyunSMSConfig.getDomain());
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //验证码
        String msgCode = getMsgCode();

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        /* 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为 */
        request.setTemplateParam("{\"code\":\" + msgCode + \"}");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(outId);

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        acsClient.getAcsResponse(request);

        boolean result = true;
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
            log.info("短信发送成功！验证码：{}", msgCode);
            //短信发送成功后存入redis
            RedisVo redisVo = new RedisVo();
            redisVo.setKey(Constant.SMS_KEY + mobile);
            redisVo.setValue(msgCode);
            redisService.save(redisVo);
        }else {
            log.info("短信发送失败！");
            result = false;
        }

        return result;
    }

    /**
     * 判断验证码是否正确
     *
     * @param mobile
     * @param msgCode
     * @return boolean true表示验证成功，false表示验证失败
     */
    public boolean verifyMsgCode(String mobile, String msgCode) {
        String redisMsgCode = redisService.get(Constant.SMS_KEY + mobile);

        boolean result = true;
        if (!msgCode.equals(redisMsgCode)) {
            result = false;
        }
        return result;
    }

    /**
     * 生成验证码
     *
     * @return String
     */
    private String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }
}
