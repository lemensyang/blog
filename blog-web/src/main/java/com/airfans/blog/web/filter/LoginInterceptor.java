package com.airfans.blog.web.filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.airfans.blog.commom.constants.UserConstants;
import com.airfans.blog.entity.user.UserEntity;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 系统路径http://xxx.xx.xx:port/projectName
        String getContextPath = request.getContextPath();
        LOGGER.info("getContextPath:" + getContextPath);

        // 当前访问路径http://xxx.xx.xx:port/projectName/xx/xx
        String getRequestURI = request.getRequestURI();
        LOGGER.info("getRequestURI:" + getRequestURI);

        UserEntity userInfo = (UserEntity) request.getSession().getAttribute(UserConstants.USER_INFO);

        if (null != userInfo) {
            return true;
        } else {
            PrintWriter writer = response.getWriter();
            StringBuilder sb = new StringBuilder();
            sb.append("<script type=\"text/javascript\">");
            // sb.append("layer.open({").append("content: '登录已过期，请重新登录'").append(",btn: ['重新登录', '留在页面']")
            // .append(",yes: function(index, layero){")
            sb.append("window.top.location.href='" + getContextPath + "/login/initLogin.htm';");
            // .append("}").append(",cancel: function(){ }").append("});");
            sb.append("</script>");
            writer.write(sb.toString());
            writer.flush();
            writer.close();

            return false;
        }
    }
    


}
