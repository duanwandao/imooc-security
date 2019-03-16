package com.imooc.web.controller;

import com.imooc.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-14 8:41
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public Map<String,Object> handlerUserNotExistException(UserNotExistException ex){
        Map<String,Object> result=new HashMap<>();
        result.put("id",ex.getId());
        result.put("message",ex.getMessage());
        return result;
    }
}
