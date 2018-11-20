package com.xjc.mapper.system;

import com.xjc.entity.system.SysUser;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {
    SysUser selectByUsername(String username);

    SysUser selectUserByUsername(String username);

    SysUser selectUserByMobilePhone(String phoneNumber);
}