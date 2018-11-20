package com.xjc.cms.controller.system;

import com.common.model.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/sys/do_login")
    public Map<String, Object> ajaxLogin(String username, String password, Boolean rememberMe) {


        return R.SUCCESS_MAP;
    }

    // @RequestMapping("/sys/logout")
    // public String logout(SysUser user, HttpServletRequest request) {
    //     request.getSession().setAttribute(R.LOGIN_USER, null);
    //     request.getSession().setAttribute("menu", null);
    //     request.getSession().invalidate();
    //     return "cmshtm/views/login";
    // }

}
