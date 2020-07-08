package com.zxw.admin.common.shrio;

import com.zxw.admin.system.user.entity.UserEntity;
import com.zxw.admin.system.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.common.shrio
 * @date 2020/6/12 13:52
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 执行授权逻辑
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 执行认证逻辑
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        //从token中取到用户名再去查用户密码
        UserEntity userEntity =userService.getUserByName(userToken.getUsername());
        if (null == userEntity){
            return null;
        }
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        return new SimpleAuthenticationInfo(userEntity, userEntity.getUserPwd(), "");
    }
}
