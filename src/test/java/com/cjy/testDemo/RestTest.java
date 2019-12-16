package com.cjy.testDemo;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.user.UserInfoResult;
import com.alibaba.fastjson.JSONObject;
import com.ss.Application;
import com.ss.jpush.config.JPushConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/16 0016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RestTest {

    @Autowired
    private JPushConfig jPushConfig;




    @Test
    public void a() {
        String appkey = jPushConfig.getAppKey();
        String masterSecret = jPushConfig.getMasterSecret();
        JMessageClient client = new JMessageClient(appkey,masterSecret);
        RegisterInfo build = RegisterInfo.newBuilder().setUsername("104426428").setPassword("123456").build();
        try {
            RegisterInfo[] registerInfos = new RegisterInfo[1];
            registerInfos[0] = build;
            String s = client.registerUsers(registerInfos);
            System.out.println(s);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
}
