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
 *
 * 登录控制器
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转到首页
     */
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return R.SUCCESS_MAP;
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            Map<String, Object> error = new HashMap<>();
            error.put("msg", msg);
            error.put("code", 500);
            return error;
        }
    }

    /**
     * 登录
     * 请求来了之后，shiro会自己去验证登录 1.若登录成功 跳转到index页面 2.若登录失败 进入这里 在login里面处理异常 提示前端页面错误信息
     */
    @RequestMapping("/login2")
    @ResponseBody
    public Map<String, Object> ajaxLogin2(HttpServletRequest request, String username, String password, Boolean rememberMe) throws Exception{
        System.out.println("HomeController.login()");
        // 此方法不处理登录成功,由shiro进行处理
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);

        Map<String, Object> returnMap = new HashMap<>();
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "用户或密码错误";
                System.out.println("else -- >" + exception);
            }
        }
        returnMap.put("msg", msg);
        returnMap.put("code", 500);
        System.out.println("");
        return returnMap;
    }

    // @RequestMapping("/sys/logout")
    // public String logout(SysUser user, HttpServletRequest request) {
    //     request.getSession().setAttribute(R.LOGIN_USER, null);
    //     request.getSession().setAttribute("menu", null);
    //     request.getSession().invalidate();
    //     return "cmshtm/views/login";
    // }

    /**
     * 403页面
     */
    @RequestMapping("/403")
    public String unauthorizedRole(){
        logger.info("------没有权限-------");
        return "/error/403";
    }
}
