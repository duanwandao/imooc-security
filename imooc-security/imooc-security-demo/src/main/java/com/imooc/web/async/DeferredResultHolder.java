package com.imooc.web.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-15 17:29
 */
@Component
@Data
public class DeferredResultHolder {

    //key可以理解为每个订单号，每个订单号对应着订单的处理结果DeferredResult<String>
    private Map<String, DeferredResult<String>> map=new HashMap<>();

}
