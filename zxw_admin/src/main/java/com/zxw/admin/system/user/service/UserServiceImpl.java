package com.zxw.admin.system.user.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxw.admin.common.CommonResult;
import com.zxw.admin.common.Utils;
import com.zxw.admin.system.user.entity.UserEntity;
import com.zxw.admin.system.user.mapper.Usermapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.system.user.service
 * @date 2020/6/12 11:33
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private Usermapper usermapper;
    @Resource
    private Utils utils;

    @Override
    public CommonResult login(UserEntity userEntity) {
        String userName = userEntity.getUserName();
        String userPwd = DigestUtils.md5Hex(userEntity.getUserPwd());
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName,userPwd);
        session.setAttribute("token", token);
        try {
            subject.login(token);
            return new CommonResult(200,"登陆成功");
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            return new CommonResult(401,"登录失败:用户名不存在");
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            return new CommonResult(401,"登录失败:密码错误");
        }
    }

    @Override
    public CommonResult logout() {
        Subject subject = SecurityUtils.getSubject();;
        if (subject.isAuthenticated()) {
            System.out.println("执行登出");
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            return new CommonResult(200,"登出成功");
        }
        return new CommonResult(400,"登出失败");
    }

    @Override
    public CommonResult getAllUser(Integer pageNum) {
        PageHelper.startPage(pageNum,6);
        List<UserEntity> list = null;
        try {
            list = usermapper.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"查询失败");
        }
        PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(list);
        return new CommonResult(200,"查到数据", pageInfo);
    }

    @Override
    public UserEntity getUserByName(String userName) {
        UserEntity userEntity = null;
        try {
            userEntity = usermapper.getUserByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userEntity;
    }

    @Override
    public CommonResult getUserById(String userId) {
        UserEntity userEntity = null;
        try {
            userEntity = usermapper.getUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"查询失败");
        }
        return new CommonResult(200,"查到数据", userEntity);
    }

    @Override
    public CommonResult postUser(HttpServletRequest request) {
        Map<String,String> reqmap = utils.requestToMap(request);
        String userId = utils.getUUID();
        String userName = reqmap.get("userName");
        String userPwd = DigestUtils.md5Hex(reqmap.get("userPwd"));
        Date addTime = new Date();
        UserEntity userEntity = new UserEntity(userId, userName, userPwd, addTime);
        int result = 0;
        try {
            result = usermapper.postUser(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(400,"添加失败");
        }
        if (result > 0){
            return new CommonResult(200,"添加成功",userEntity);
        }else {
            return new CommonResult(400,"添加失败");
        }
    }
}
