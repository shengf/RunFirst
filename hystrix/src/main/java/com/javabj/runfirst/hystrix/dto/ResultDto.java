package com.javabj.runfirst.hystrix.dto;

/**
 * 返回结果DTO
 *
 * @author William.Sheng（【Java北京】公众号作者，公众号ID：java_bj）
 * @date: 2018/5/10 16:47
 * @version 1.0.0
 *
 */
public class ResultDto<T> {

    private Integer status;

    private T data;

    public Integer getStatus() {
        return status;
    }

    public ResultDto setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultDto setData(T data) {
        this.data = data;
        return this;
    }

    public static ResultDto build(){
        return new ResultDto();
    }

    public static ResultDto build(Integer status) {
        return new ResultDto().setStatus(status);
    }
}