package com.example.apacheshiroDemo;

import com.example.apacheshiroDemo.model.Permission;
import com.example.apacheshiroDemo.model.Role;
import com.example.apacheshiroDemo.model.User;
import com.example.apacheshiroDemo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if(!CollectionUtils.isEmpty(roles)){
            for (Role role : roles) {
                Set<Permission> permissions = role.getPermissions();
                if(!CollectionUtils.isEmpty(permissions)){
                    for (Permission permission : permissions) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return info;
    }

    //登陆认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUsername(username);
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
