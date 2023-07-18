package org.chench.springboot.scaffolding.config;

import org.chench.springboot.scaffolding.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web相关配置
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.config.WebConfig
 * @date 2023.07.17
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 路径模式为"/**"表明所有请求都要经过拦截器处理
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
    }
}
