package com.xjc.service.test;

import com.xjc.model.test.TblTestCondition;

import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/16 14:19.
 */
public interface ITestService {
    Map<String,Object> queryUserList(TblTestCondition condition);
}
