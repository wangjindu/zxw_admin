package com.zxw.admin.common.shrio;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.common.shrio
 * @date 2020/6/12 14:06
 */
@Configuration
public class ShrionConfig {

    /**
     * 创建Realm
     * */
    @Bean
    public UserRealm getRealm() {
        return new UserRealm();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //权限管理，配置主要是realm的管理认证
    @Bean
    public DefaultSecurityManager securityManager(UserRealm userRealm) {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //filter工厂，设置对应的过滤条件和跳转条件
    /**
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //添加shiro的内置过滤器
        /*
            anon: 无需认证就可访问
            authc：必须认证才能访问
            user：必须拥有记住我功能才能访问
            perms: 拥有对某个资源的权限才能访问
            role:拥有某个角色权限才能访问
       */

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/src/page/*", "authc");
        filterMap.put("/logout", "logout");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录请求
        bean.setLoginUrl("/login");
        return bean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(60 * 30 * 1000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        // tomcat的JESSIONID自动生成模块
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }
}
