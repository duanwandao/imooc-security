package com.imooc.security.core.properties;

import lombok.Data;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 20:09
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

}
