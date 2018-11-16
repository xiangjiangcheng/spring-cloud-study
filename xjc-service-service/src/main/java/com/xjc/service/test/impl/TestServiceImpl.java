package com.xjc.service.test.impl;

import com.xjc.mapper.test.TblUserMapper;
import com.xjc.model.test.TblTestCondition;
import com.xjc.service.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/16 14:20.
 */
@Service
public class TestServiceImpl implements ITestService{

    @Autowired
    private TblUserMapper userMapper;

    public Map<String, Object> queryUserList(TblTestCondition condition) {
        Map<String, Object> returnMap = new HashMap<String, Object>();



        return returnMap;
    }
}
