package com.airfans.blog.web.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airfans.blog.commom.constants.UserConstants;
import com.airfans.blog.commom.util.StringUtils;
import com.airfans.blog.entity.user.UserEntity;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/login")
public class LoginController {

    public Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/initLogin")
    public String initLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login/initLogin";
    }

    @RequestMapping("/login")
    @ResponseBody
    public JSONObject login(HttpServletRequest request, HttpServletResponse response, UserEntity userInfo) {
        JSONObject resJo = new JSONObject();
        if (!StringUtils.isEmptyOrNull(userInfo.getUserName())) {
            request.getSession().setAttribute(UserConstants.USER_INFO, userInfo);
            resJo.put("flag", 1);
            resJo.put("home", "home/main.htm");
        } else {
            resJo.put("flag", 0);
        }
        return resJo;
    }

}
