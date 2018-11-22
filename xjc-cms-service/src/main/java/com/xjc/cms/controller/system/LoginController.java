package com.xjc.cms.controller.system;

import com.common.model.R;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/19 11:21.
 * <p>
 * 登录控制器
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转到登录页面
     */
    @GetMapping("/g_login")
    public String login() {
        return "login";
    }

    /**
     * 登录逻辑
     */
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> ajaxLogin(String username, String password, Boolean rememberMe) {
        /**
         * 使用Shiro 编写登录逻辑
         * 请求来了之后，shiro会自己去验证登录 1.若登录成功 跳转到index页面 2.若登录失败 进入这里 在login里面处理异常 提示前端页面错误信息
         */
        // 1.获取subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装前端传过来的用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);

        // 错误信息
        Map<String, Object> error = new HashMap<>();
        String msg = "";
        error.put("code", 500);
        try {
            // 3.执行登录方法 执行下面subject的login方法，一定会执行Realm里面的认证方法
            // 没有任何异常：表示认证成功！
            // 返之，认证失败 shiro会抛异常 这里我们捕获异常，做对应的页面跳转
            subject.login(token);
            return R.SUCCESS_MAP;
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            error.put("msg", e.getMessage());
            return error;
        } catch (AuthenticationException e) {
            logger.info("//目前逻辑走不到这里");
            msg = "用户名或者密码错误";
            error.put("msg", msg);
            return error;
        }
    }

    /**
     * 403页面
     */
    @RequestMapping("/403")
    public String unauthorizedRole() {
        logger.info("------没有权限-------");
        return "/error/403";
    }
}
