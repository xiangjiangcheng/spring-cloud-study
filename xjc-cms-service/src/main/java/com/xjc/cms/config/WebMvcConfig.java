package com.xjc.cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/19 11:54.
 *
 * Spring Boot 系列（四）静态资源处理 https://www.cnblogs.com/magicalSam/p/7189476.html
 *
 * url拦截实现权限控制 https://blog.csdn.net/u013087513/article/details/74979321?locationNum=3&fps=1
 *
 * 这样使用代码的方式自定义目录映射，并不影响Spring Boot的默认映射，可以同时使用。
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        super.addResourceHandlers(registry);
    }
}
