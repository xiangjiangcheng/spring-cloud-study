package com.common.model;

/**
 * 存放Redis键 常量类
 */
public class Rkey {
    /**
     * 前缀
     */
    public static final String KEY_PREFIX_VALUE = "info:core:value:";
    public static final String KEY_PREFIX_SET = "info:core:set:";
    public static final String KEY_PREFIX_LIST = "info:core:list:";
    public static final String KEY_PREFIX_HASH = "info:core:hash:";

    /**
     * 分隔符 -
     */
    public final static String SPECIAL_SPLIT_LINE =  "_";

    //hash key =  md5, fileUrl
    public static String FILE_MD5 = "file_md5";

    //用户信息
    public static final String USER_INFO = "user_info";



}
