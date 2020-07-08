package com.zxw.admin.basic.phone.controller;

import com.zxw.admin.basic.phone.service.PhoneService;
import com.zxw.admin.common.CommonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.phone.controller
 * @date 2020/6/16 16:27
 */
@RestController
@RequestMapping(value = "/phone")
public class PhoneController {

    @Resource
    private PhoneService phoneService;

    @DeleteMapping(value = "/deletePhoneById/{phoneId}")
    public CommonResult deletePhoneById(@PathVariable(value = "phoneId") String phoneId){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return phoneService.deletePhoneById(phoneId);
    }

    @GetMapping(value = "/getAllPhone/{pageNum}")
    public CommonResult getAllPhone(@PathVariable(value = "pageNum") Integer pageNum){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return phoneService.getAllPhone(pageNum);
    }
}
