package com.airfans.blog.web.controller;


import javax.servlet.http.HttpServletRequest;

import com.airfans.blog.entity.user.UserEntity;
import com.airfans.blog.service.IUserService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("seller_id", 1);
        Page<UserEntity> page = new Page<UserEntity>(1, 10);
        userService.selectPage(page, wrapper);
        return "/demo/list_demo.ftl";
    }
}
