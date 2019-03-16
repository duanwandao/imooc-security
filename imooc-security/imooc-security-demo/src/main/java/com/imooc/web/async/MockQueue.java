package com.imooc.web.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-15 17:33
 */
@Component
@Slf4j
@Data
public class MockQueue {

    private String placeOrder;

    private String completeOrder;

    public void setPlaceOrder(String placeOrder)throws Exception{
        new Thread(() -> {
            log.info("接到下单请求: " + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder=placeOrder;
            log.info("下单请求处理完毕: " + placeOrder);
        }).start();

    }

}
