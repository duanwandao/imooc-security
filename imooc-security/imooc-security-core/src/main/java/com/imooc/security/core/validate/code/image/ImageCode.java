package com.imooc.security.core.validate.code.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 13:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode {

    private BufferedImage image;

    private String code;  //随机数存到session中

    private LocalDateTime expireTime;  //有效时间

    public ImageCode (BufferedImage image,String code,
                      int expireIn) {
        this.image=image;
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried(){
        //如果 过期时间 在 当前日期 之前，则验证码过期
        return LocalDateTime.now().isAfter(expireTime);
    }
}
