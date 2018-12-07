package com.airfans.blog.web;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

public class BaseController<T> {

    /**
     * 统一处理分页返回前端数据 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param page
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public JSONObject returnPage(Page<T> page) {
        JSONObject jo = new JSONObject();
        jo.put("status", 0);
        jo.put("page", page);
        return jo;
    }

    public Page<T> getPage(HttpServletRequest request) {
        Page<T> page = null;
        try {
            int curr = Integer.parseInt(request.getParameter("curr"));
            int size = Integer.parseInt(request.getParameter("limit"));
            String sort = request.getParameter("sort");
            String field = request.getParameter("field");
            page = new Page<T>(curr, size, field);
            page.setAsc(sort.equals("asc"));

        } catch (Exception e) {
            page = new Page<T>(1, 10, "");
        }
        return page;
    }
}
