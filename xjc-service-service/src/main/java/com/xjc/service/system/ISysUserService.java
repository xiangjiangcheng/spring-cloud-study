package com.xjc.service.system;

import com.xjc.entity.system.SysUser;

public interface ISysUserService {
    SysUser selectUserByUsername(String username);

    SysUser selectUserByMobilePhone(String mobilePhone);
}
