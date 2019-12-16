package com.ss.jpush.service;

import com.alibaba.fastjson.JSONObject;
import com.ss.common.utils.ResultApp;
import com.ss.jpush.config.JPushConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/16 0016.
 */
@Service
public class IMService {

    @Autowired
    private JPushConfig jPushConfig;

    @Transactional
    public ResultApp register(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username","1044264288");
        paramMap.put("password","123456");
        JSONObject forObject = restTemplate.getForObject(jPushConfig.getRequestUrl() + "/" + jPushConfig.getVersion() + "users", JSONObject.class, paramMap);
        System.out.println(forObject);
        return null;
    }


}
