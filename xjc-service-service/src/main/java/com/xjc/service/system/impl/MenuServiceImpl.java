package com.xjc.service.system.impl;

import com.xjc.entity.system.SysMenu;
import com.xjc.mapper.system.SysMenuMapper;
import com.xjc.service.system.IMenuService;
import com.xjc.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(Integer userId) {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<String>();
        for (String perm : perms)
        {
            if (!ObjectUtils.isEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenu> selectMenusByUserId(Integer userId) {
        List<SysMenu> menus = menuMapper.selectMenusByUserId(userId);
        // 处理菜单
        List<SysMenu> childPerms = TreeUtils.getChildPerms(menus, 0);

        return childPerms;
    }
}
