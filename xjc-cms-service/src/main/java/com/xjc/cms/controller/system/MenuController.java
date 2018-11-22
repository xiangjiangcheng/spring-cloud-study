package com.xjc.cms.controller.system;

import com.xjc.entity.system.SysMenu;
import com.xjc.service.system.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/19 10:13.
 *
 * 菜单控制器
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController {

    private String prefix = "system/menu/";

    @Autowired
    private IMenuService menuService;

    /**
     * 跳转到菜单页面
     */
    @GetMapping("")
    public String menu() {
        return prefix + "menu";
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> returnMap = new HashMap<>();
        List<SysMenu> menuList = menuService.selectAllMenu();
        returnMap.put("data", menuList);
        return returnMap;
    }



}
