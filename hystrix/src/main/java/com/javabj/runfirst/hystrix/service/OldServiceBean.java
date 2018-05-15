package com.javabj.runfirst.hystrix.service;

import org.springframework.stereotype.Service;

/**
 * 不使用hystrix的service bean
 *
 * @author William.Sheng（【Java北京】公众号作者，公众号ID：java_bj）
 * @date: 2018/5/10 16:43
 * @version 1.0.0
 *
 */
@Service
public class OldServiceBean {

    public String doSomething(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OldServiceBean.doSomething return.";
    }

    public String querySomething(){
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "OldServiceBean.querySomething return.";
    }
}