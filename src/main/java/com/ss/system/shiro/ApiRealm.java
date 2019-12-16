package com.ss.system.shiro;

import com.alibaba.fastjson.JSONObject;
import com.ss.common.utils.ResultApp;
import com.ss.common.utils.redis.RedisTemplateUtil;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/7/11.
 */
public class ApiRealm extends AccessControlFilter {


    @Autowired
    RedisTemplateUtil redisTemplateUtil;



    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取当前用户token
        String token = getRequestToken((HttpServletRequest)request);
        //获取当前请求路径URL
        String url = ((HttpServletRequest) request).getServletPath();
        //判断请求路径如果是登录,放行
        if ("/api/appUserAdressCommon/upQrCodePath".equals(url)||"/api/user/loginByUsername".equals(url)|| "/api/user/regist".equals(url) || "/api/sms/sendSms".equals(url)|| "/api/sms/loginByPhone".equals(url)){
            return true;
        }
        //判断是否有token
        if (!StringUtils.isEmpty(token)){
            String s = redisTemplateUtil.get(token);
            if(!StringUtils.isEmpty(s)){
                redisTemplateUtil.setForTimeMIN(token,s,30);
                return true;
            }
        }
        response.setCharacterEncoding("utf-8");
        ResultApp r = ResultApp.failWithMsg("token无效");
        response.getWriter().write(JSONObject.toJSONString(r));
        return false;
        //TODO 测试阶段
//        return true;
    }

    private String getRequestToken(HttpServletRequest request){
        //默认从请求头中获得token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
//        if(StringUtils.isEmpty(token)){
//            token = request.getParameter("token");
//        }
        return token;
    }

    // 登录失败 写出信息
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpResponse.getWriter().write("失败");
    }
}
