package com.xjc.service.system;

import java.util.Set;

public interface IMenuService {
    Set<String> selectPermsByUserId(Integer userId);
}
