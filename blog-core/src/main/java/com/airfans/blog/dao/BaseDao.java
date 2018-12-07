package com.airfans.blog.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public int delete() {
        return sqlSessionTemplate.delete("");
    }

}
