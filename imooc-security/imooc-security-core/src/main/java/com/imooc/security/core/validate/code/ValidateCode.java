package com.imooc.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 验证码信息封装类
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 21:04
 */
@Data
@AllArgsConstructor
public class ValidateCode implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1588203828504660915L;

    private String code;   //随机数存到session中

    private LocalDateTime expireTime;   //有效时间

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
