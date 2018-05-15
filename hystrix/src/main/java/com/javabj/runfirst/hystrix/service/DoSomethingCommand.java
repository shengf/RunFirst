package com.javabj.runfirst.hystrix.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * dosomething hystrix封装
 *
 * @author William.Sheng（【Java北京】公众号作者，公众号ID：java_bj）
 * @date: 2018/5/10 18:31
 * @version 1.0.0
 *
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DoSomethingCommand extends HystrixCommand<String> {

    @Autowired
    private OldServiceBean oldServiceBean;

    protected DoSomethingCommand() {
        super(Setter
                //将command进行分组，主要用于统计以便于我们进行监控
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group-oldService"))
                //用于唯一区分一个命令对象，并且唯一标识熔断器、metric等资源。我们可以为每一个远程方法都建立一个独一无二的key。如果key相同，意味着此时会共用熔断器和metric资源
                .andCommandKey(HystrixCommandKey.Factory.asKey("oldService-DoSomethingCommand"))
                //用来标示线程池，每一个command默认配备一个线程池（线程隔离模式下）。如果key相同，则会共用一个线程池资源
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("do-threadpool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //隔离策略
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        //设置调用者等待命令执行的超时限制，超过此时间，HystrixCommand被标记为TIMEOUT，并执行回退逻辑，默认值1000
                        .withExecutionTimeoutInMilliseconds(2000)
                        //断路器生效
                        .withCircuitBreakerEnabled(true))
        );
    }


    @Override
    protected String run() throws Exception {
        return oldServiceBean.doSomething();
    }

    @Override
    protected String getFallback(){
        return "-----------fallback----------";
    }
}