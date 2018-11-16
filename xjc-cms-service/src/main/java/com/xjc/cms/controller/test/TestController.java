package com.xjc.cms.controller.test;

import com.xjc.model.test.TblTestCondition;
import com.xjc.service.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/16 14:24.
 */
@RequestMapping("/sys/test/")
@Controller
public class TestController {

    @Autowired
    private ITestService testService;

    /**
     * hello
     */
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello 幺儿";
    }

    /**
     * 根据条件查询列表
     */
    @RequestMapping(value = "q_user_list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getGoodsList(TblTestCondition condition) {
        return testService.queryUserList(condition);
    }
}
