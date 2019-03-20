package com.imooc.security.core.properties;

import lombok.Data;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-19 19:49
 */
@Data
public class BrowserProperties {

    //设定默认值如果没有指定loginPage则访问该页面
    private String loginPage = "/imooc-signIn.html";

    private LoginResponseType loginType = LoginResponseType.JSON;  //默认返回json

}
