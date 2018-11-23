package com.xjc.cms.controller.system;

import com.common.model.R;
import com.xjc.entity.system.SysUser;
import com.xjc.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 用户控制器
 */

@Controller
@RequestMapping("/system/user")
public class SysUserController {

    private String prefix = "system/user/";

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 跳转到用户管理界面
     */
    @GetMapping("")
    public String user() {
        return prefix + "user";
    }

    @PostMapping("/add")
    public Map<String, Object> add(SysUser user) {

        return R.SUCCESS_MAP;
    }


}
