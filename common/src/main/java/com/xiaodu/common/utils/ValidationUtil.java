package com.xiaodu.common.utils;

import com.xiaodu.common.exception.BadRequestException;

import java.util.Optional;

/**
 * 验证工具
 *
 * @author shen
 * @date 2019-4-22
 */
public class ValidationUtil {

    /**
     * 验证空
     *
     * @param optional
     */
    public static void isNull(Optional optional, String entity, String parameter, Object value) {
        if (!optional.isPresent()) {
            String msg = entity
                    + " 不存在 "
                    + "{ " + parameter + ":" + value.toString() + " }";
            throw new BadRequestException(msg);
        }
    }

    /**
     * 验证是否为邮箱
     *
     * @param string
     * @return
     */
    public static boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return string.matches(regEx1);
    }

    /**
     * 验证是否是手机号
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile){
        if (mobile == null) {
            return false;
        }
//        String ph = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        String ph = "^1[3|4|5|6|7|8][0-9]{9}$";
        return mobile.matches(ph);

    }
}
