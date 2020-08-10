package com.example.apacheshiroDemo.service;

import com.example.apacheshiroDemo.model.User;

/**
 * @author george.zhang
 * @date 2020/8/10 20:31
 * @Description
 */
public interface UserService {
    User findByUsername(String username);
}
