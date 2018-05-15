package com.javabj.runfirst.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用hystrix的service bean 2
 *
 * @author William.Sheng（【Java北京】公众号作者，公众号ID：java_bj）
 * @date: 2018/5/10 19:44
 * @version 1.0.0
 *
 */
@Service
public class New2ServiceBean {

    @Autowired
    private OldServiceBean oldServiceBean;

    @HystrixCommand(groupKey = "group-oldService",
            commandKey = "oldService-DoSomethingCommand",
            threadPoolKey = "do-threadpool",
            fallbackMethod = "doFallback",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "200"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            }

    )
    public String doSomething(){
        String result =  oldServiceBean.doSomething();
        System.out.println(result);
        return result;
    }

    @HystrixCommand(groupKey = "group-oldService2",
            commandKey = "oldService-QuerySomethingCommand",
            threadPoolKey = "query-threadpool",
            fallbackMethod = "queryFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "200"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            }

    )
    public String querySomething(){
        String result =  oldServiceBean.querySomething();
        System.out.println(result);
        return result;
    }

    protected String doFallback(){
        System.out.println("-----------doFallback----------");
        return "-----------doFallback----------";
    }

    protected String queryFallback(){
        System.out.println("-----------queryFallback----------");
        return "-----------queryFallback----------";
    }
}