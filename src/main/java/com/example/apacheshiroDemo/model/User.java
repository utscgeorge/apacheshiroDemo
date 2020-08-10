package com.example.apacheshiroDemo.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author george.zhang
 * @date 2020/8/10 20:24
 * @Description
 */
@Data
public class User {
    private Integer uid;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
