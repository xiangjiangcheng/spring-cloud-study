package com.xjc.service.system.impl;

import com.xjc.entity.system.SysUser;
import com.xjc.mapper.system.SysUserMapper;
import com.xjc.model.system.UserConstants;
import com.xjc.service.system.ILoginService;
import com.xjc.service.system.ISysUserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录校验方法
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 登录
     */
    @Override
    public SysUser login(String username, String password) {
        // 验证码校验



        // 查询用户信息
        SysUser sysUser = sysUserService.selectUserByUsername(username);
        if (sysUser == null && maybeMobilePhoneNumber(username)) {
            // 手机号登录
            sysUser = sysUserService.selectUserByMobilePhone(username);
        }

        if (sysUser == null) {
            // 用户不存在 抛shiro自带异常
            throw new UnknownAccountException("用户名不存在");
        }
        validatePassword(sysUser, password);
        return sysUser;
    }

    private void validatePassword(SysUser sysUser, String password) {
        String loginName = sysUser.getLoginName();
        // 缓存
        // AtomicInteger retryCount = loginRecordCache.get(loginName);
        if (!matches(sysUser, password)) {
            // 密码不匹配 抛shiro自带异常
            throw new IncorrectCredentialsException("密码错误");
        }
    }

    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }

    public boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex().toString();
    }

    public static void main(String[] args) {
        //System.out.println(new PasswordService().encryptPassword("admin", "admin123", "111111"));
        System.out.println(new LoginServiceImpl().encryptPassword("ry", "admin123", "222222"));
        System.out.println(new LoginServiceImpl().encryptPassword("ly", "admin123", "123456"));
    }

}
