package com.xjc.service.system.impl;

import com.xjc.entity.system.SysUser;
import com.xjc.mapper.system.SysUserMapper;
import com.xjc.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    public SysUser selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    public SysUser selectUserByMobilePhone(String phoneNumber) {
        return userMapper.selectUserByMobilePhone(phoneNumber);
    }
}
