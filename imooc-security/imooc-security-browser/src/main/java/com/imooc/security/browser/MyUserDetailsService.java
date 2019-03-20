package com.imooc.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-19 16:18
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名："+username);
        //指定用户授权登录信息
        //密码加密，使用的是盐值加密
        String password = passwordEncoder.encode("123456");
        log.info("数据库中密码是："+password);
        //User类是Spring内置的一个类，实现了UserDetails接口，而这个接口是UserDetailSerice的子接口
        return new User(username, password, true, true, true, true
                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
