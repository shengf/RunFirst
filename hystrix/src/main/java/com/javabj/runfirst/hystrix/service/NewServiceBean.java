package com.javabj.runfirst.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用hystrix的service bean 1
 *
 * @author William.Sheng（【Java北京】公众号作者，公众号ID：java_bj）
 * @date: 2018/5/10 19:44
 * @version 1.0.0
 *
 */
@Service
public class NewServiceBean {

    @Autowired
    private QuerySomethingCommand querySomethingCommand;

    @Autowired
    private DoSomethingCommand doSomethingCommand;

    public String doSomething(){
        return new DoSomethingCommand().execute();
        //return doSomethingCommand.execute();
    }

    public String querySomething(){
        //String result = new QuerySomethingCommand().execute();
        //return result;
        return querySomethingCommand.execute();

    }

}