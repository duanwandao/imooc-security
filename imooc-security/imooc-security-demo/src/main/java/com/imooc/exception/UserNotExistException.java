package com.imooc.exception;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-14 8:38
 */
public class UserNotExistException extends RuntimeException{

    private String id;

    public UserNotExistException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
