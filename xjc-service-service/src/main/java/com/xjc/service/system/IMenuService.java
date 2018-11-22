package com.xjc.service.system;

import com.xjc.entity.system.SysMenu;

import java.util.List;
import java.util.Set;

public interface IMenuService {
    Set<String> selectPermsByUserId(Integer userId);

    List<SysMenu> selectMenusByUserId(Integer userId);
}
