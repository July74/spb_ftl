package com.july.baseweb;

import com.july.baseweb.interceptor.CsrfAttackInterceptor;
import com.july.baseweb.interceptor.LoginInterceptor;

import com.july.common.annotion.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * mvc配置
 *
 * @author July
 * @date 2017/12/18
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private CsrfAttackInterceptor csrfAttackInterceptor;

    /**
     * 配置静态访问资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/static/");
    }

    @Override
    @RequireLogin
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/user/login").setViewName("user/login");
        registry.addViewController("/user/add").setViewName("user/add");
        registry.addViewController("/user/edit").setViewName("user/edit");
        registry.addViewController("/user/list").setViewName("user/list");

        super.addViewControllers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);
        registry.addInterceptor(csrfAttackInterceptor);
        super.addInterceptors(registry);
    }
}
