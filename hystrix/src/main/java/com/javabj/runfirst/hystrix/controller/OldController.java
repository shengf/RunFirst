package com.javabj.runfirst.hystrix.controller;

import com.javabj.runfirst.hystrix.dto.ResultDto;
import com.javabj.runfirst.hystrix.service.OldServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 不使用Hystrix的入口controller
 *
 * @author William.Sheng（【Java北京】公众号作者，公众号ID：java_bj）
 * @date: 2018/5/10 16:41
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("/old")
public class OldController {

    @Autowired
    private OldServiceBean oldServiceBean;

    @RequestMapping(value = "/doSth", method = {RequestMethod.POST})
    @ResponseBody
    public ResultDto<String> doSomething(HttpServletRequest request, HttpServletResponse response){
        String result = oldServiceBean.doSomething();
        ResultDto<String> resultDto = ResultDto.build(0).setData(result);
        return resultDto;
    }

    @RequestMapping(value = "/querySth", method = {RequestMethod.GET})
    @ResponseBody
    public ResultDto<String> querySomething(HttpServletRequest request, HttpServletResponse response){
        String result = oldServiceBean.querySomething();
        ResultDto<String> resultDto = ResultDto.build(0).setData(result);
        return resultDto;
    }

}