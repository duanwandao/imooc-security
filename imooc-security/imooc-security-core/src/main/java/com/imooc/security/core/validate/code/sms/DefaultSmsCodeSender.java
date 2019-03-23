package com.imooc.security.core.validate.code.sms;

/**
 * 默认的短信验证码发送器
 * @Description
 * @auther 断弯刀
 * @create 2019-03-23 16:58
 */
public class DefaultSmsCodeSender implements SmsCodeSender{

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
