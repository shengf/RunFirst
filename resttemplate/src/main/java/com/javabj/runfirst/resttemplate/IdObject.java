package com.javabj.runfirst.resttemplate;

import java.io.Serializable;

/**
 * 返回结果封装对象
 *
 * @author William.Sheng（【Java北京】公众号作者，公众号ID：java_bj）
 * @date: 2018/5/8 11:57
 * @version 1.0.0
 *
 */
public class IdObject implements Serializable {

    private static final long serialVersionUID = 2610847136547979604L;

    private Integer code;
    private String message;
    private String id;

    public IdObject() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}