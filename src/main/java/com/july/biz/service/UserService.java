package com.july.biz.service;

import com.july.biz.entity.UserEntity;
import com.july.biz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> getUserList() {
        return userMapper.selectUser();
    }
}
