package com.zxw.admin.basic.sign.controller;

import com.zxw.admin.basic.sign.service.SignService;
import com.zxw.admin.common.CommonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zxw.admin.basic.sign.controller
 * @date 2020/6/28 17:57
 */
@RestController
@RequestMapping(value = "/sign")
public class SignController {

    @Resource
    private SignService signService;

    @GetMapping(value = "/getAllSign/{pageNum}")
    public CommonResult getAllSign(@PathVariable("pageNum") Integer pageNum){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return signService.getAllSign(pageNum);
    }

    @DeleteMapping(value = "/deleteSignById/{signId}")
    public CommonResult deleteSignById(@PathVariable("signId") String signId){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return new CommonResult(401,"未登录");
        }
        return signService.deleteSignById(signId);
    }
}
