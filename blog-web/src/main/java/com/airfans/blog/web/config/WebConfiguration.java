package com.airfans.blog.web.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.airfans.blog.web.filter.LoginInterceptor;

import freemarker.template.TemplateException;

/**
 *
 配置springmvc拦截及freemarker
 */
@Configuration
@ConfigurationProperties(prefix = "spring.freemarker")
public class WebConfiguration implements WebMvcConfigurer {
    private Logger LOGGER = LogManager.getLogger(WebConfiguration.class);

    @Autowired
    LoginInterceptor loginInterceptor;

    /**
     * FreeMarker全局变量
     */
    private Map<String, Object> variables = new HashMap<>();

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }

    /**
     * SpringMvc拦截及FreeMarker全局变量配置
     */
    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet dispatcherServlet,
            FreeMarkerConfigurer freeMarkerConfigurer) {
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<DispatcherServlet>(
                dispatcherServlet);
        registration.addUrlMappings("*.htm");

        try {
            freeMarkerConfigurer.getConfiguration().setSharedVaribles(variables);
            freeMarkerConfigurer.afterPropertiesSet();
        } catch (IOException | TemplateException e) {
            LOGGER.error("配置DispatcherServlet及FreeMarker失败", e);
        }
        return registration;
    }

    @Bean(name = "fppErrorPageFilter")
    public ErrorPageFilter errorPageFilter() {
        LOGGER.error("");
        return new ErrorPageFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(loginInterceptor);
        interceptor.excludePathPatterns("/login/**");
        interceptor.excludePathPatterns("/error**");
        // 拦截配置
        interceptor.addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean<ErrorPageFilter> filterRegistrationBean(
            @Qualifier("fppErrorPageFilter") ErrorPageFilter filter) {
        FilterRegistrationBean<ErrorPageFilter> filterRegistrationBean = new FilterRegistrationBean<ErrorPageFilter>();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("*.do");
        filterRegistrationBean.setEnabled(false);
        filterRegistrationBean.addUrlPatterns("*.htm");
        return filterRegistrationBean;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

}
