package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Date;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-15 8:49
 */
/*@Aspect
@Component*/
public class TimeAspect {

    //执行 任何返回值  此类  任何方法 任何参数
    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        System.out.println("time aspect start");

        Object[] args=proceedingJoinPoint.getArgs();
        for (Object arg:args){
            System.out.println("arg is "+arg);
        }

        long start=new Date().getTime();

        Object object=proceedingJoinPoint.proceed();

        System.out.println("time aspect 耗时:"+(new Date().getTime()-start));

        System.out.println("time aspect end");

        return object;
    }
}
