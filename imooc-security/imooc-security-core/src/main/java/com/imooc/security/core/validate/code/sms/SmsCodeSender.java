package com.imooc.security.core.validate.code.sms;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-23 16:57
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
