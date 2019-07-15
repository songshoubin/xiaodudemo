package com.xiaodu.sms.service;

import com.aliyuncs.exceptions.ClientException;
import com.xiaodu.common.exception.BadRequestException;
import com.xiaodu.common.utils.StringUtil;
import com.xiaodu.sms.utils.AliyunSMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SMS服务类
 *
 * @author songshoubin
 * @date 2019-7-15
 */
@Service
public class SMSService {

    @Autowired
    private AliyunSMSUtil aliyunSMSUtil;

    /**
     * 发送短信
     *
     * @param mobile
     * @param signName
     * @param templateCode
     * @param templateParam
     * @param outId
     * @return boolean true表示发送成功，false表示发送失败
     * @throws ClientException
     */
    public boolean sendSms(String mobile, String signName, String templateCode, String templateParam, String outId) {
        if(StringUtil.isBlank(mobile)) {
            throw new BadRequestException("发送的手机号码不能为空!");
        }

        boolean result = true;
        try {
            result = aliyunSMSUtil.sendSms(mobile, signName, templateCode, templateParam, outId);
        }catch (Exception e) {
            throw new BadRequestException("发送短信遇到错误，请重试!");
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
        if (StringUtil.isBlank(mobile) || StringUtil.isBlank(msgCode)) {
            throw new BadRequestException("手机号码和验证码不能为空!");
        }
        return aliyunSMSUtil.verifyMsgCode(mobile, msgCode);
    }
}
