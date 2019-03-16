package com.imooc.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-16 9:39
 */
@Component
@Slf4j
//ContextRefreshedEvent整个spring完全初始化完毕的事件
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        //无限的while循环，要是不单开线程的话，就会阻塞整个系统的启动
        new Thread(() -> {
            while (true){
                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                    String orderNumber=mockQueue.getCompleteOrder();
                    log.info("返回订单处理结果:"+orderNumber);
                    //setResult()中为最终要返回的信息
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order:" + orderNumber + " success");
                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
