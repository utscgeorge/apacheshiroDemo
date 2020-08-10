package com.example.apacheshiroDemo.service;

import com.example.apacheshiroDemo.mapper.UserMapper;
import com.example.apacheshiroDemo.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author george.zhang
 * @date 2020/8/10 20:32
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
