package com.xjc.service.system.impl;

import com.xjc.entity.system.SysRole;
import com.xjc.mapper.system.SysRoleMapper;
import com.xjc.service.system.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(Integer userId) {

        List<SysRole> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<String>();
        for (SysRole perm : perms)
        {
            if (ObjectUtils.isEmpty(perms))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}
