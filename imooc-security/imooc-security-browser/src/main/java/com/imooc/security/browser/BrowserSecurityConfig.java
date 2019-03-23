package com.imooc.security.browser;

import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 浏览器环境下安全配置主类
 * @Description
 * @auther 断弯刀
 * @create 2019-03-19 16:05
 */
@Configuration
@Slf4j
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    //让系统使用我们自定义 而不是系统默认的配置
    @Autowired
    private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;

    //记住我后的登录页面
    @Autowired
    private UserDetailsService userDetailsService;

    //注入密码加密的类
    @Bean
    public PasswordEncoder passwordEncoder() {
        //这里如果是自己编写的加密 则调用自己的类 方法有编码和解码验证方法
        return new SCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    //记住我的功能
    @Bean
    public PersistentTokenRepository getPersistentTokenRespsitory() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        // 配置数据源
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        //启动时创建一张表，这个参数到第二次启动时必须注释掉，因为已经创建了一张表
        //jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
        return jdbcTokenRepositoryImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        //在UsernamePasswordAuthenticationFilter 过滤器前 加一个过滤器 来搞验证码
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() //认证
            .loginPage("/authentication/require")  //设置登录页面
                .loginProcessingUrl("/authentication/form") //遇到该请求则进行user password认证
                .successHandler(imoocAuthenticationSuccessHandler)  //成功后 使用我们自己的处理器处理
                .failureHandler(imoocAuthenticationFailureHandler)  //设置失败处理器
             .and()
                .rememberMe()
                    .tokenRepository(getPersistentTokenRespsitory())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())         // 配置Cookie过期时间
                    .userDetailsService(userDetailsService)
            .and()
                .authorizeRequests()   //授权
                //当访问这个路径的时候不需要身份认证 除了它其他的是需要身份认证
                .antMatchers("/authentication/require"
                        , securityProperties.getBrowser().getLoginPage()
                        , "/code/image", "/code/sms").permitAll()
                .anyRequest()    //任何请求
                .authenticated()   //都需要认证
            .and()
                .csrf().disable();
    }
}
