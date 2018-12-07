package com.airfans.blog.service;

import com.airfans.blog.entity.user.UserEntity;
import com.airfans.blog.dao.user.UserMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserService  extends ServiceImpl<UserMapper,UserEntity> implements IUserService {
}
