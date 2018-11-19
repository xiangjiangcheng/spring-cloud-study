package com.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/19 11:26.
 *
 * 通用常量类
 * 魔法值
 */
public class R {

    // 用户状态 0：正常 1：锁定
    public final static Integer USER_STATE_NORMAL = 0;

    public final static Integer USER_STATE_LOCK = 1;

    public static final Map<String, Object> SUCCESS_MAP = new HashMap<String, Object>();

    static {
        SUCCESS_MAP.put("success", true);
        SUCCESS_MAP.put("code", 200);
    }
}
