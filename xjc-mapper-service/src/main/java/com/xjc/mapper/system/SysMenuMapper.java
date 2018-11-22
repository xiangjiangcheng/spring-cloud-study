package com.xjc.mapper.system;

import com.xjc.entity.system.SysMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {
    List<String> selectPermsByUserId(Integer userId);

    List<SysMenu> selectMenusByUserId(Integer userId);
}