package com.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/19 10:05.
 *
 * 错误信息常量类
 */
public class ErrorKey {
    //非法请求
    public static final int INVALID_REQUEST = 95;
    //非法token
    public final static int INVALID_TOKEN = 401;
    //没有权限
    public final static int UN_AUTHORIZATION = 102;
    //系统错误
    public final static int SYSTEM_ERROR = 100;
    //非法参数
    public final static int INVALID_PARAMETER = 101;
    //文件上传错误
    public static final int UPLOAD_FILE_ERROR = 300;

    public static Map<Integer, Object> errorMap = new HashMap<Integer, Object>();

    static {
        errorMap.put(SYSTEM_ERROR, "系统错误");
        errorMap.put(INVALID_PARAMETER, "非法参数");
        errorMap.put(INVALID_TOKEN, "登录信息过期，请重新登录");
        errorMap.put(INVALID_REQUEST, "非法请求");
        errorMap.put(UPLOAD_FILE_ERROR, "文件上传错误");
    }
}
