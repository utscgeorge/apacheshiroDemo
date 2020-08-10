package com.example.apacheshiroDemo.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author george.zhang
 * @date 2020/8/10 20:23
 * @Description
 */
@Data
public class Role {
    private Integer rid;
    private String rname;
    private Set<Permission> permissions = new HashSet<>();
    private Set<User> users = new HashSet<>();
}
