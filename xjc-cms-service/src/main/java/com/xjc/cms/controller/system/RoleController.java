package com.xjc.cms.controller.system;

import com.xjc.service.system.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/22 10:13.
 *
 * 角色控制器
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {

    private String prefix = "system/role/";

    @Autowired
    private IRoleService roleService;

    /**
     * 跳转到角色管理页面
     */
    @GetMapping("")
    public String role() {
        return prefix + "role";
    }



}
