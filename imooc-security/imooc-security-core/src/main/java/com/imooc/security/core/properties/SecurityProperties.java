package com.imooc.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-19 19:48
 */
//意思是这个类会读取配置文件中所有以imooc.security开头的配置项
//其中browser的配置项会读取到BrowserProperties的对象中去
@ConfigurationProperties(prefix = "imooc.security")
@Component
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
}
