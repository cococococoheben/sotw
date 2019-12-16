package com.ss.common.utils.easemob;

import com.alibaba.fastjson.JSONObject;
import com.ss.common.utils.easemob.entity.Easemob_POST_Token;
import com.ss.common.utils.easemob.entity.Easemob_POST_User;
import com.ss.common.utils.redis.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 环信操作类
 */
@Component
public class EasemobAPI {

    @Autowired
    RedisTemplateUtil redisTemplateUtil;

    /**
     * 获取 APP 管理员 Token
     * @return
     */
    public String getToken(){
        //缓存中取出token
        String access_token = redisTemplateUtil.get(EasemobConfig.TOKEN_KEY);

        if(access_token == null){

            RestTemplate restTemplate = new RestTemplate();
            //参数
            Easemob_POST_Token easemob_post_token = new Easemob_POST_Token();
            easemob_post_token.setGrant_type("client_credentials");
            easemob_post_token.setClient_id(EasemobConfig.Client_ID);
            easemob_post_token.setClient_secret(EasemobConfig.Client_Secret);

            String url = EasemobConfig.createURI("/token");

            try {
                JSONObject body = restTemplate.postForEntity(url, easemob_post_token, JSONObject.class).getBody();
                System.out.println(body.toJSONString());

                access_token = body.get("access_token").toString();
                Long expires_in = Long.parseLong(body.get("expires_in").toString());
                //存入缓存 过期时间为token返回的过期时间减10秒钟
                redisTemplateUtil.setForTimeSEC(EasemobConfig.TOKEN_KEY, access_token, expires_in - 10);

            } catch (HttpClientErrorException e) {
                System.out.println("环信->获取Token->错误:");
                System.out.println(e.getResponseBodyAsString());
            }




        }

        return access_token;
    }

    /**
     * 注册单个用户
     * @param eeasemob_post_user
     * @return
     *
     *
     *
     * Path: /{org_name}/{app_name}/users
     * HTTP Method: POST
     * URL Params: 无
     * Request Headers: {“Content-Type”:”application/json”,”Authorization”:”Bearer ${token}”}
     * Request Body: {“username”:”${用户名}”,”password”:”${密码}”}
     *
     *
     */
    public boolean  insertUser(Easemob_POST_User eeasemob_post_user){

        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.add("Authorization", "Bearer "+getToken());

        HttpEntity<Easemob_POST_User> requestEntity = new HttpEntity(eeasemob_post_user,requestHeaders);

        RestTemplate restTemplate = new RestTemplate();

        String url = EasemobConfig.createURI("/users");

        try {
            JSONObject body = restTemplate.postForEntity(url, requestEntity, JSONObject.class).getBody();
            System.out.println(body.toJSONString());
        } catch (HttpClientErrorException e) {
            System.out.println("环信->注册单个用户->错误:");
            System.out.println(e.getResponseBodyAsString());
        }

        return true;
    }

    /**
     * 批量注册用户
     * @param easemob_post_users
     * @return
     */
    public boolean insertUser(List<Easemob_POST_User> easemob_post_users){

        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.add("Authorization", "Bearer "+getToken());

        HttpEntity<List<Easemob_POST_User>> requestEntity = new HttpEntity(easemob_post_users,requestHeaders);

        RestTemplate restTemplate = new RestTemplate();

        String url = EasemobConfig.createURI("/users");

        try {
            JSONObject body = restTemplate.postForEntity(url, requestEntity, JSONObject.class).getBody();
            System.out.println(body.toJSONString());
        } catch (HttpClientErrorException e) {
            System.out.println("环信->批量注册用户->错误:");
            System.out.println(e.getResponseBodyAsString());
        }


        return true;
    }

    /**
     * 删除单个用户
     * @param username
     * @return
     */
    public boolean deleteUser(String username){

        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.add("Authorization", "Bearer "+getToken());

        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        RestTemplate restTemplate = new RestTemplate();

        String url = EasemobConfig.createURI("/users/"+username);

        try {
            JSONObject body = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, JSONObject.class).getBody();
            System.out.println(body.toJSONString());
        } catch (HttpClientErrorException e) {
            System.out.println("环信->删除单个用户->错误:");
            System.out.println(e.getResponseBodyAsString());
        }

        return true;
    }







}
