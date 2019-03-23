package com.imooc.security.core.properties;

import lombok.Data;

/**
 * 验证码配置
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 20:09
 */
@Data
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();

}
