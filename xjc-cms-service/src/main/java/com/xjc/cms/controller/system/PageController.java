package com.xjc.cms.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/19 10:13.
 *
 * 页面跳转控制器
 */
@Controller
@RequestMapping("/sys/")
public class PageController {

    public static final String TEST_PATH = "test/";

    public static final String SYSTEM_PATH = "system/";

    @RequestMapping("g_test")
    public String goTest() {
        return TEST_PATH + "test";
    }

    @RequestMapping("g_login")
    public String goLogin() {
        return "login";
    }

    @RequestMapping("g_main")
    public String goMain() {
        return "main";
    }


}
