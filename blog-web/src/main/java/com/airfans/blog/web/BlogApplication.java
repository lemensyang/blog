package com.airfans.blog.web;

import javax.servlet.ServletContext;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.WebApplicationContext;

/**
 * SpringBoot 初始化
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = { "com.airfans.blog" })
public class BlogApplication extends SpringBootServletInitializer {
    @Override
    protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
        servletContext.setInitParameter("idsConfigFilePath", "/WEB-INF/classes/conf/ids/ids-config.xml");
        servletContext.setInitParameter("idsAccessPolicyFilePath", "/WEB-INF/classes/conf/ids/ids-accessPolicy.xml");
        servletContext.setInitParameter("idsRedisFilePath", "/WEB-INF/classes/conf/ids/ids-redis.xml");
        return super.createRootApplicationContext(servletContext);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BlogApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
