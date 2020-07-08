package com.zxw.admin.system.user.controller;

import com.zxw.admin.common.CommonResult;
import com.zxw.admin.system.user.entity.UserEntity;
import com.zxw.admin.system.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.system.user.controller
 * @date 2020/6/12 11:53
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/login")
    public CommonResult login(@RequestBody UserEntity userEntity){
        return userService.login(userEntity);
    }

    @PostMapping(value = "/logout")
    public CommonResult logout(){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return userService.logout();
    }

    @GetMapping(value = "/getAllUser/{pageNum}")
    public CommonResult getAllUser(@PathVariable(value = "pageNum") Integer pageNum){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return userService.getAllUser(pageNum);
    }

    @GetMapping(value = "/getUserById/{userId}")
    public CommonResult getUserById(@PathVariable(value = "userId") String userId){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return userService.getUserById(userId);
    }

    @PostMapping(value = "/postUser")
    public CommonResult postUser(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return userService.postUser(request);
    }
}
