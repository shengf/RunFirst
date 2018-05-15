package com.javabj.runfirst.resttemplate;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestTemplate
 *
 * @author William.Sheng（【Java北京】公众号作者，公众号ID：java_bj）
 * @date: 2018/5/10 14:30
 * @version 1.0.0
 *
 */
public class RestTemplateUtils {

    private RestTemplate restTemplate = new RestTemplate();

    public String getSN() {
        Map<String, String> paramMap = new HashMap<>();
        //id生成策略 : 用户ID:0, 交易流水号:1
        paramMap.put("strategy", "1");
        paramMap.put("prefix", "230001");
        paramMap.put("appkey", "payment");
        paramMap.put("expires", "1525851849");
        paramMap.put("nonce", "xyB");
        paramMap.put("signature", "1102fd1068");

        //logger.info("before request2IdGenerator, param={}", paramMap);
        IdObject idObject = restTemplate.getForObject("http://10.16.208.50:11823/id/generate?" +
                        "strategy={strategy}&prefix={prefix}&appkey={appkey}&expires={expires}&nonce={nonce}&signature={signature}",
                IdObject.class, paramMap);
        //logger.info("after request2IdGenerator, idObject:{}", idObject);
        if(idObject.getCode() != null && idObject.getCode().intValue() == 0) {
            return idObject.getId();
        }

        return null;
    }
    public String getSN2() {
        Map<String, String> paramMap = new HashMap<>();
        //id生成策略 : 用户ID:0, 交易流水号:1
        paramMap.put("strategy", "1");
        paramMap.put("prefix", "230001");
        paramMap.put("appkey", "payment");
        paramMap.put("expires", "1525851849");
        paramMap.put("nonce", "xyB");
        paramMap.put("signature", "1102fd1068");

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        //logger.info("before request2IdGenerator, param={}", paramMap);
        IdObject idObject = restTemplate.getForObject("http://10.16.208.50:11823/id/generate?" +
                        "strategy={strategy}&prefix={prefix}&appkey={appkey}&expires={expires}&nonce={nonce}&signature={signature}",
                IdObject.class, paramMap);
        //logger.info("after request2IdGenerator, idObject:{}", idObject);
        if(idObject.getCode() != null && idObject.getCode().intValue() == 0) {
            return idObject.getId();
        }

        return null;
    }

    public static void main(String[] args){
        RestTemplateUtils restTemplateUtils = new RestTemplateUtils();
        String sn = restTemplateUtils.getSN();
        //String sn = restTemplateUtils.getSN2();
        System.out.println(sn);
    }

}