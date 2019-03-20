package com.imooc.security.core.properties;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-19 19:57
 */

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
//让BrowserProperties生效
public class SecurityCoreConfig {
}
