package com.xjc.cms.controller.system;

import com.xjc.common.security.ShiroUtils;
import com.xjc.entity.system.SysMenu;
import com.xjc.entity.system.SysUser;
import com.xjc.service.system.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/22 13:41.
 *
 * 首页控制器
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IMenuService menuService;

    /**
     * 跳转到系统首页
     */
    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        // 从shiro中取身份信息
        SysUser user = ShiroUtils.getUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUserId(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("user", user);
        // model.addAttribute("copyrightYear", ruoYiConfig.getCopyrightYear());
        return "/index";
    }


}
