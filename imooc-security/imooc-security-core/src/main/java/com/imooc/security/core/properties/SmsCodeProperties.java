package com.imooc.security.core.properties;

import lombok.Data;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-22 17:58
 */
@Data
public class SmsCodeProperties {

    /**
     * 验证码长度
     */
    private int length = 6;
    /**
     * 过期时间
     */
    private int expireIn = 60;
    /**
     * 要拦截的url，多个url用逗号隔开，ant pattern
     */
    private String url;

}
