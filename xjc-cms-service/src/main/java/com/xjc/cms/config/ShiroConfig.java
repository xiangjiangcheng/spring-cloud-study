package com.xjc.cms.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.xjc.cms.shiro.realm.UserRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/20 17:15.
 *
 * shiro 权限配置加载
 */
@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件过滤器
     * 1：配置shiro安全管理器接口securityManage;
     * 2：shiro连接约束配置filterChainDefinitions;
     */
    @Bean public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //shiroFilterFactoryBean对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 配置shiro安全管理器 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 身份认证失败，则直接跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl("/g_login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权时跳转的界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // Shiro连接约束配置，即过滤链的定义
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 从上向下顺序判断
        // 1.对静态资源设置匿名访问
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/templates/**", "anon");

        // 配置退出过滤器,shiro去清除session,具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/sys/g_login", "anon");
        filterChainDefinitionMap.put("/sys/test/q_user_list", "anon");

        // 授权过滤器
        // 注意：当前授权失败后，shiro会自动跳转到未授权界面
        // filterChainDefinitionMap.put("/sys/g_main", "perms[user:add]");

        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        logger.debug("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * shiro安全管理器设置realm认证
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm());
        // //注入ehcache缓存管理器;
        // securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    /**
     * 自定义Realm
     * 身份认证realm; (账号密码校验；权限等)
     *
     */
    @Bean
    public UserRealm userRealm()
    {
        UserRealm userRealm = new UserRealm();
        // userRealm.setCacheManager(cacheManager);
        return userRealm;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置shiroDialect 用于thymeleaf模板引擎和shiro框架的整合 thymeleaf中使用shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }
}
