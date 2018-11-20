package com.xjc.service.system;

import java.util.Set;

public interface IRoleService {
    Set<String> selectRoleKeys(Integer userId);
}
