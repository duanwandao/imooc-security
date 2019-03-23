package com.imooc.security.core.properties;

import lombok.Data;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 20:06
 */
@Data
public class ImageCodeProperties {

    private int width = 67;
    private int height = 23;
    private int length = 4;    //验证码长度
    private int expireIn = 60;   //失效时间

    private String url = "";
}
