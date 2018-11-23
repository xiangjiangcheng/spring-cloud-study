package com.xjc.cms.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/23 17:31.
 *
 * druid 配置.
 */
@Configuration
public class DruidConfiguration {

    /**
     * 注册一个StatViewServlet 相当于在web.xml中声明了一个servlet
     */
    @Bean
    public ServletRegistrationBean druidServlet()
    {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        // 访问路径 /monitor/druid/index.html
        reg.addUrlMappings("/monitor/druid/*");
        /** 白名单 */
        // reg.addInitParameter("allow", "10.211.61.45,127.0.0.1,123.207.20.136");
        /** IP黑名单(共同存在时，deny优先于allow) */
        // reg.addInitParameter("deny", "10.211.61.4");
        /** 是否能够重置数据 禁用HTML页面上的“Reset All”功能 */
        reg.addInitParameter("resetEnable", "false");
        return reg;
    }

    /**
     * 注册一个：filterRegistrationBean 相当于在web.xml中声明了一个Filter
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        /** 添加过滤规则. */
        filterRegistrationBean.addUrlPatterns("/*");
        /** 监控选项滤器 */
        filterRegistrationBean.addInitParameter("DruidWebStatFilter", "/*");
        /** 添加不需要忽略的格式信息. */
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/monitor/druid/*");
        /** 配置profileEnable能够监控单个url调用的sql列表 */
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        /** 当前的cookie的用户 */
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        /** 当前的session的用户 */
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }

}
