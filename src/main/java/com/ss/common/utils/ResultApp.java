package com.ss.common.utils;

import io.swagger.annotations.ApiModel;

/**
 * 通用响应对象
 */
@ApiModel(description = "响应对象")
public class ResultApp<T> {

    public static final Integer ERROR_CODE = 1;

    public static final Integer SUCCESS_CODE = 0;

    public static final String SUCCESS_MESSAGE = "成功";

    public static final String ERROR_MESSAGE = "请求异常";

    public static final String NO_PARAMS = "缺少必要参数";



    private Integer msg;

    private String error;

    private T content;

    private ResultApp(Integer msg, String error, T content) {
        this.msg = msg;
        this.error = error;
        this.content = content;
    }

    public ResultApp() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    private ResultApp(Integer msg, String error) {
        this(msg, error, null);
    }

    private ResultApp(T content) {
        this(SUCCESS_CODE, "", content);
    }

    private ResultApp(String error) {
        this(SUCCESS_CODE, error, null);
    }
    public static <T> ResultApp<T> success() {
        return new ResultApp<>();
    }

    public static <T> ResultApp<T> tokenResponse(T content) {

        return new ResultApp<>(2000, "", content);
    }

    public static <T> ResultApp<T> tokenResponsePast(String error) {

        return new ResultApp(1, error, "");
    }


    public static <T> ResultApp<T> successWithData(T content) {
        return new ResultApp<>(content);
    }
    public static <T> ResultApp<T> successWithMsg(String error) {
        return new ResultApp(error);
    }

    public static <T> ResultApp<T> failWithMsg(String error) {
        return new ResultApp<>(ERROR_CODE, error, null);
    }

    public static <T> ResultApp<T> failWithMsg(Integer msg, String error) {
        return new ResultApp<>(msg, error, null);
    }

    public static <T> ResultApp<T> noParams() {
        return new ResultApp<>(ERROR_CODE, NO_PARAMS,null);
    }

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
