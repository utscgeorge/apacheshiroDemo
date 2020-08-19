package com.example.apacheshiroDemo;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {


    /**
     * @Qualifier:使用自定义的bean对象作为参数
     * @Bean:将自定义的对象声明成指定名称的bean
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        //没有权限访问时跳转的URL
        bean.setUnauthorizedUrl("/unauthorized");

        //定义请求URL和拦截器种类的对应map
        LinkedHashMap<String,String> filterChainMap = new LinkedHashMap<>();
        /*
        DefaultFilter中定义了一些认证器枚举
        *       匿名访问:anon(AnonymousFilter.class),
                form表单认证:authc(FormAuthenticationFilter.class),
                http认证:authcBasic(BasicHttpAuthenticationFilter.class),
                注销拦截器:logout(LogoutFilter.class),
                创建session拦截器:noSessionCreation(NoSessionCreationFilter.class),
                权限验证:perms(PermissionsAuthorizationFilter.class),
                port(PortFilter.class),
                rest(HttpMethodPermissionFilter.class),
                角色验证:roles(RolesAuthorizationFilter.class),
                ssl(SslFilter.class),
                user(UserFilter.class);
        *
        * */
        //登陆成功跳转使用authc校验器
        filterChainMap.put("/index","authc");
        //登陆页面无需任何校验
        filterChainMap.put("/login","anon");
        bean.setFilterChainDefinitionMap(filterChainMap);
        return bean;
    }


    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher credentialMatcher){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(credentialMatcher);
        return authRealm;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher();
    }


    /**
     *  告诉spring使用shiro定义的授权校验器
     *
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

}
