package com.xjc.mapper.system;

import com.xjc.entity.system.SysRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {
    List<SysRole> selectRolesByUserId(Integer userId);
}