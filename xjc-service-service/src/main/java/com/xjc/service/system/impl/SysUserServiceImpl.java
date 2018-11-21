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

    @Override
    public SysUser selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
        // return userMapper.selectByPrimaryKey(1);
    }

    @Override
    public SysUser selectUserByMobilePhone(String phoneNumber) {
        return userMapper.selectUserByMobilePhone(phoneNumber);
    }
}
