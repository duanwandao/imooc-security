package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 14:53
 */
public class ValidateCodeException extends AuthenticationException {


    /**
     *
     */
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
