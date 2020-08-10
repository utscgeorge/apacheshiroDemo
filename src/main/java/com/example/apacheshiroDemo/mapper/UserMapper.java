package com.example.apacheshiroDemo.mapper;

import com.example.apacheshiroDemo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author george.zhang
 * @date 2020/8/10 20:29
 * @Description
 */
public interface UserMapper {
    User findByUsername(@Param("username") String username);
}
