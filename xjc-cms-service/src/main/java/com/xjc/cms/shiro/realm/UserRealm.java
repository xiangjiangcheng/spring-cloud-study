package com.xjc.cms.shiro.realm;

import com.xjc.common.security.ShiroUtils;
import com.xjc.entity.system.SysUser;
import com.xjc.service.system.ILoginService;
import com.xjc.service.system.IMenuService;
import com.xjc.service.system.IRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm 处理登录 权限
 * 
 */
public class UserRealm extends AuthorizingRealm
{
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ILoginService loginService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        Integer userId = ShiroUtils.getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色加入AuthorizationInfo认证对象
        info.setRoles(roleService.selectRoleKeys(userId));
        // 权限加入AuthorizationInfo认证对象
        info.setStringPermissions(menuService.selectPermsByUserId(userId));
        return info;
    }

    /**
     * 登录认证
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        logger.info("shiro -> realm ： doGetAuthenticationInfo 身份认证 开始校验账号密码是否正确");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null)
        {
            password = new String(upToken.getPassword());
        }

        SysUser user = null;
        try
        {
            user = loginService.login(username, password);
        }
        /*catch (CaptchaException e)
        {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (UserNotExistsException e)
        {
            throw new UnknownAccountException(e.getMessage(), e);
        }
        catch (UserPasswordNotMatchException e)
        {
            throw new IncorrectCredentialsException(e.getMessage(), e);
        }
        catch (UserPasswordRetryLimitExceedException e)
        {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        }
        catch (UserBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }
        catch (RoleBlockedException e)
        {
            throw new LockedAccountException(e.getMessage(), e);
        }*/
        catch (Exception e)
        {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        // 验证通过
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

}
