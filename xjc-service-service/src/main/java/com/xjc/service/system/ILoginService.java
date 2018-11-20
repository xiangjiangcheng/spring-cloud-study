package com.xjc.service.system;

import com.xjc.entity.system.SysUser;

public interface ILoginService {
    SysUser login(String username, String password) throws Exception;
}
