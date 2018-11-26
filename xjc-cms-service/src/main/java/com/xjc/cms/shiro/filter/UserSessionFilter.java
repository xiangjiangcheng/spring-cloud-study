package com.xjc.cms.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/26 16:50.
 */
@Configuration
public class UserSessionFilter extends AccessControlFilter{

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        try {
            // Subject subject = getSubject(request, response);
            // if (subject == null) {
            //     // 没有登录
            //     return false;
            // }
        } catch (Exception e) {

        }


        HttpSession session = WebUtils.toHttp(request).getSession();
        // Object sessionUsername = session.getAttribute(GlobalConstant.SESSION_AUTH_LOGIN_USERNAME);
        // if (sessionUsername == null) {
        //     // 你自己的逻辑
        // }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return true;
    }
}
