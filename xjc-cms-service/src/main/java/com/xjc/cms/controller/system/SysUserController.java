package com.xjc.cms.controller.system;

import com.common.model.R;
import com.xjc.entity.system.SysUser;
import com.xjc.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/sys/user/")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("add")
    public Map<String, Object> add(SysUser user) {

        return R.SUCCESS_MAP;
    }


}
